package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.domain.synergy.SynergyStatus;
import java.util.ArrayList;
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

    public int size() {
        return champions.size();
    }

    public int value() {
        return status.score();
    }

    public boolean isFull() {
        return champions.size() == level;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "level=" + level +
                ", champions=" + champions +
                ", status=" + status +
                '}';
    }
}
