package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.repository.Repository;
import java.util.List;

public class BasicCombinator implements Combinator {

    private final List<Champion> championRepository;

    public BasicCombinator(Repository<Champion> championRepository) {
        this.championRepository = championRepository.getAllList();
    }

    public List<Deck> combine(int maximumNumber, List<Champion> champion, List<Synergy> synergy) {
        CandidateList candidateList = new CandidateList();
        Deck current = new Deck(maximumNumber, champion, synergy);
        dfs(candidateList, current, championRepository, 0);
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
