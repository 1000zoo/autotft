package com.zoo.autotft.service;

import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.dto.RecommendDeckDto;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CandidateList {

    private final static int MAX_SIZE = 10;

    private final Queue<Deck> candidateList = new PriorityQueue<>(Comparator.comparingInt(Deck::score));

    public void add(Deck deck) {
        candidateList.add(deck);
        if (candidateList.size() > MAX_SIZE) {
            candidateList.poll();
        }
    }

    public List<RecommendDeckDto> getResults() {
        return candidateList.stream().map(Deck::getResultDto).toList();
    }

    @Override
    public String toString() {
        return "CandidateList{" +
                "candidateList=" + candidateList +
                '}';
    }
}
