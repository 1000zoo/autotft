package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.ChampionSynergy;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public final class Champion implements Comparable<Champion> {

    private final String name;
    private final int cost;
    private List<ChampionSynergy> synergies = null;

    public Champion(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public void setSynergies(List<ChampionSynergy> synergies) {
        if (this.synergies == null) {
            this.synergies = synergies;
        }
    }

    public boolean containsSynergy(Synergy synergy) {
        if (synergies == null) {
            return false;
        }
        return synergies.stream().anyMatch(championSynergy -> synergy.equals(championSynergy.synergy()));
    }

    public List<Synergy> getSynergies() {
        return synergies.stream().map(ChampionSynergy::synergy).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Champion champion = (Champion) o;
        return Objects.equals(name, champion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Champion o) {
        if (cost == o.cost) {
            return name.compareTo(o.name);
        }
        return Integer.compare(cost, o.cost);
    }

    @Override
    public String toString() {
        return "Champion{" +
                "name='" + name + '\'' +
                ", synergies=" + getSynergies() +
                '}';
    }
}
