package com.zoo.autotft.domain.synergy;

import java.util.ArrayList;

public class Origin extends Synergy {

    public Origin(String name, ActivateConditions conditions) {
        this.name = name;
        this.conditions = conditions;
        this.champions = new ArrayList<>();
    }
}
