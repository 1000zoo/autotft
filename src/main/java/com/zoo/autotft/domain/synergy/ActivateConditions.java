package com.zoo.autotft.domain.synergy;

import java.util.List;

public record ActivateConditions(List<Integer> conditions) {

    public int getActivatedState(int count) {
        return -1;
    }
}
