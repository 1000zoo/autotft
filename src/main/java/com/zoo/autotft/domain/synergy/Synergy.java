package com.zoo.autotft.domain.synergy;

import com.zoo.autotft.domain.Champion;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public abstract class Synergy {

    protected final String name;
    protected final ActivateConditions conditions;
    protected List<ChampionSynergy> champions = null;

    protected Synergy(String name, ActivateConditions conditions) {
        this.name = name;
        this.conditions = conditions;
    }

    public void setChampions(List<ChampionSynergy> champions) {
        if (this.champions == null) {
            this.champions = champions;
        }
    }

    public boolean containsChampion(Champion champion) {
        if (champions == null) {
            return false;
        }
        return champions.stream().anyMatch(championSynergy -> champion.equals(championSynergy.champion()));
    }

    public List<Champion> getAllChampionList() {
        return champions.stream().map(ChampionSynergy::champion).toList();
    }

    public int getActivatedState(int count) {
        return conditions.getActivatedState(count);
    }

    public int getActivatedScore(int count) {
        return conditions.getActivatedScore(count);
    }

    public boolean isActivated(int count) {
        return getActivatedScore(count) > 0;
    }

    public boolean isUnique() {
        return getClass() == Unique.class;
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

    @Override
    public String toString() {
        return name;
    }
}
