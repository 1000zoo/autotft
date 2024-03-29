package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.domain.synergy.SynergyStatus;
import com.zoo.autotft.dto.RecommendDeckDto;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Deck {

    private final int level;
    private final List<Champion> champions;
    private final SynergyStatus status;

    public Deck(int level, List<Champion> champions, List<Synergy> status) {
        validate(level, champions);
        this.level = level;
        this.champions = new ArrayList<>(champions);
        this.status = SynergyStatus.of(status);
        setting();
    }

    public Deck(Deck forCopy) {
        level = forCopy.level;
        champions = new ArrayList<>(forCopy.champions);
        status = new SynergyStatus(forCopy.status);
    }

    private void setting() {
        champions.forEach(champion -> champion.getSynergies().forEach(status::put));
    }

    private void validate(int level, List<Champion> champions) {
        if (level < champions.size()) {
            throw new IllegalStateException("이미 많은 챔피언들이 속해있습니다.");
        }
    }

    public void add(Champion champion) {
        champions.add(champion);
        champion.getSynergies().forEach(status::put);
    }

    public void remove(Champion champion) {
        if (!champions.contains(champion)) {
            return;
        }
        champions.remove(champion);
        champion.getSynergies().forEach(status::remove);
    }

    public int size() {
        return champions.size();
    }

    public int score() {
        return status.score();
    }

    public boolean isFull() {
        return champions.size() == level;
    }

    public boolean contains(Champion champion) {
        return champions.contains(champion);
    }

    public List<Champion> getAllPossibleChampions() {
        return status.getAllPossibleChampions(new HashSet<>(champions));
    }

    public RecommendDeckDto getResultDto() {
        return new RecommendDeckDto(champions, status.getSortedStatus());
    }

    @Override
    public String toString() {
        return "Deck{" +
                "champions= " + champions +
                ", score= " + score() +
                ", status=" + status.toString() +
                '}' + "\n";
    }
}
