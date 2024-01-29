package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.repository.Repository;
import java.util.List;

public class BasicCombinator implements Combinator {

    private final List<Champion> allChampions;

    public BasicCombinator(Repository<Champion> championRepository) {
        this.allChampions = championRepository.getAllList();
    }

    @Override
    public List<Deck> combine(int maximumNumber, List<Champion> champions, List<Synergy> synergies) {
        CandidateList candidateList = new CandidateList();
        Deck current = new Deck(maximumNumber, champions, synergies);
        dfs(candidateList, current, allChampions, 0);
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
