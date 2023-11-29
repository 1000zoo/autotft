package com.zoo.autotft.domain.synergy;

import java.util.ArrayList;

public class Class extends Synergy {

    public Class(String name, ActivateConditions conditions) {
        this.name = name;
        this.conditions = conditions;
        this.champions = new ArrayList<>();
    }
}
