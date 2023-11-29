package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.ChampionSynergy;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.List;
import java.util.Objects;
import lombok.Getter;

@Getter
public final class Champion {

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
}
