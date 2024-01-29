package com.zoo.autotft.service;

import com.zoo.autotft.domain.Deck;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class CandidateList {

    private final static int MAX_SIZE = 10;

    private final Queue<Deck> candidateList = new PriorityQueue<>(Comparator.comparingInt(Deck::value));

    public void add(Deck deck) {
        if (candidateList.size() >= MAX_SIZE) {
            candidateList.poll();
        }
        candidateList.add(deck);
    }

    public List<Deck> getResults() {
        return new ArrayList<>(candidateList);
    }

    @Override
    public String toString() {
        return "CandidateList{" +
                "candidateList=" + candidateList +
                '}';
    }
}
