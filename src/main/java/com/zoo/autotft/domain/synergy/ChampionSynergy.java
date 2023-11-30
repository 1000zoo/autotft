package com.zoo.autotft.domain.synergy;

import com.zoo.autotft.domain.Champion;

public record ChampionSynergy(Champion champion, Synergy synergy) {

    public boolean hasSameSynergy(Synergy synergy) {
        return synergy.equals(this.synergy);
    }
}
