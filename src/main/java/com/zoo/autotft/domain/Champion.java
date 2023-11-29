package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.ChampionSynergy;
import java.util.List;
import java.util.Objects;

public final class Champion {

    private final String name;
    private final int cost;
    private List<ChampionSynergy> synergies = null;

    public Champion(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String name() {
        return name;
    }

    public int cost() {
        return cost;
    }

    public void setSynergies(List<ChampionSynergy> synergies) {
        if (this.synergies != null) {
            this.synergies = synergies;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Champion) obj;
        return Objects.equals(this.name, that.name) &&
                this.cost == that.cost &&
                Objects.equals(this.synergies, that.synergies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost, synergies);
    }

    @Override
    public String toString() {
        return "Champion[" +
                "name=" + name + ", " +
                "cost=" + cost + ", " +
                "synergies=" + synergies + ']';
    }
}
