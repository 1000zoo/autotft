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
        // 현재 조합의 시너지 활성 정도가 어느 정도인지 평가하는 지표
        // 정확한 공식은 추후에 생각
        return 0;
    }

    @Override
    public String toString() {
        return "SynergyStatus{" +
                "status=" + status +
                '}';
    }
}
