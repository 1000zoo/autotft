package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.dto.RecommendDeckDto;
import com.zoo.autotft.repository.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentCombinator implements Combinator {

    private final ExecutorService executor;
    private final List<Champion> allChampions;

    public ConcurrentCombinator(Repository<Champion> championRepository) {
        int cores = Runtime.getRuntime().availableProcessors();
        this.executor = Executors.newFixedThreadPool(cores);
        this.allChampions = championRepository.getAllList();
    }

    @Override
    public List<RecommendDeckDto> combine(int maximumNumber, List<Champion> baseChampions, List<Synergy> synergies) {
        CandidateList candidateList = new CandidateList();
        Deck current = new Deck(maximumNumber, baseChampions, synergies);
        List<Champion> remainingChampions = new ArrayList<>(allChampions);
        remainingChampions.removeAll(baseChampions);

        // 초기 작업 분할
        for (int i = 0; i < remainingChampions.size(); i++) {
            int finalI = i;
            executor.submit(() -> dfs(candidateList, new Deck(current), remainingChampions, finalI));
        }

        // 모든 작업이 완료될 때까지 기다립니다.
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        return candidateList.getResults();
    }

    private void dfs(CandidateList candidateList, Deck current, List<Champion> remainingChampions, int index) {
        if (current.isFull()) {
            candidateList.add(new Deck(current));
            return;
        }

        for (int i = index; i < remainingChampions.size(); i++) {
            Champion champion = remainingChampions.get(i);
            if (!current.contains(champion)) {
                current.add(champion);
                dfs(candidateList, current, remainingChampions, i + 1);
                current.remove(champion);
            }
        }
    }
}
