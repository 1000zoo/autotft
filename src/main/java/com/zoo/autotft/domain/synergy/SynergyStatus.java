package com.zoo.autotft.domain.synergy;

import com.zoo.autotft.domain.Champion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SynergyStatus {

    private final Map<Synergy, Integer> status;

    public SynergyStatus() {
        status = new HashMap<>();
    }

    public SynergyStatus(SynergyStatus forCopy) {
        status = new HashMap<>(forCopy.status);
    }

    public static SynergyStatus of(List<Synergy> synergyList) {
        SynergyStatus synergyStatus = new SynergyStatus();
        for (Synergy synergy : synergyList) {
            synergyStatus.put(synergy);
        }
        return synergyStatus;
    }

    public void put(Synergy key) {
        status.put(key, status.getOrDefault(key, 0) + 1);
    }

    public void put(Champion champion) {
        champion.getSynergies().forEach(this::put);
    }

    public boolean remove(Synergy key) {
        if (!status.containsKey(key)) {
            return false;
        }
        status.put(key, status.get(key) - 1);
        if (status.get(key) == 0) {
            status.remove(key);
        }
        return true;
    }

    public int size() {
        return status.values().stream().reduce(Integer::sum).orElseThrow();
    }

    public int score() {
        int score = 0;
        for (Synergy synergy : status.keySet()) {
            if (synergy.isUnique()) {
                continue;
            }
            score += synergy.isActivated(status.get(synergy)) ? 1 : 0;
        }
        return score;
    }

    public List<Champion> getAllPossibleChampions(Set<Champion> alreadyContain) {
        Set<Champion> set = new HashSet<>();
        for (Synergy synergy : status.keySet()) {
            set.addAll(synergy.getAllChampionList());
        }
        set.removeAll(alreadyContain);
        return new ArrayList<>(set);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        List<Synergy> list = status.keySet().stream()
                .sorted((s1, s2) -> s1.compareTo(s2) != 0 ? s1.compareTo(s2) : status.get(s2) - status.get(s1))
                .toList();

        for (Synergy synergy : list) {
            sb.append(synergy.name).append(":").append(status.get(synergy)).append(", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
