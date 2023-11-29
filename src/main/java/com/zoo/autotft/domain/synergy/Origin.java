package com.zoo.autotft.domain.synergy;

import java.util.ArrayList;

public class Origin extends Synergy {

    public Origin(String name) {
        this.name = name;
        this.champions = new ArrayList<>();
    }
}
