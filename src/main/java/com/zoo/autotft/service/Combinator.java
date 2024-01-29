package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.repository.Repository;
import java.util.List;

public class Combinator {

    private final static int MAXIMUM_CANDIDATE_SIZE = 10;

    private final List<Champion> championRepository;
    private final List<Synergy> synergyRepository;

    public Combinator(Repository<Champion> championRepository, Repository<Synergy> synergyRepository) {
        this.championRepository = championRepository.getAllList();
        this.synergyRepository = synergyRepository.getAllList();
    }

    public List<Deck> combine(int maximumNumber, List<Champion> champion, List<Synergy> synergy) {
        CandidateList candidateList = new CandidateList();
        Deck current = new Deck(maximumNumber, champion, synergy);

        return candidateList.getResults();
    }

    private void dfs(CandidateList candidateList, Deck current) {
        if (current.isFull()) {

        }
    }
}
