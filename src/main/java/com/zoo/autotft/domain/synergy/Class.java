package com.zoo.autotft.domain.synergy;

import java.util.Objects;

public class Class extends Synergy {

    public Class(String name, ActivateConditions conditions) {
        super(name, conditions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Synergy synergy = (Synergy) o;
        return Objects.equals(name, synergy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
