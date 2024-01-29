package com.zoo.autotft.domain.synergy;

import java.util.List;

public record ActivateConditions(List<Integer> conditions) {

    public int getActivatedState(int count) {
        return conditions.stream()
                .filter(condition -> condition <= count)
                .reduce((a, b) -> b).orElse(0);
    }

    public int getActivatedScore(int count) {
        for (int i = 0; i < conditions.size(); i++) {
            if (count < conditions.get(i)) {
                return i;
            }
        }
        return conditions.size();
    }
}
