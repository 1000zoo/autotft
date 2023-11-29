package com.zoo.autotft.domain.synergy;

import java.util.List;
import lombok.Getter;

@Getter
public abstract class Synergy {

    protected String name;
    protected List<ChampionSynergy> champions;
}
