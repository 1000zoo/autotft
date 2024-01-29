package com.zoo.autotft.domain.synergy;

import com.zoo.autotft.domain.Champion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            score += synergy.getActivatedScore(status.get(synergy));
        }
        return score;
    }

    @Override
    public String toString() {
        return "SynergyStatus{" +
                "status=" + status +
                '}';
    }
}
