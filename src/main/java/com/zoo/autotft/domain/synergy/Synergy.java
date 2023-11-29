package com.zoo.autotft.domain.synergy;

import java.util.List;
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
}
