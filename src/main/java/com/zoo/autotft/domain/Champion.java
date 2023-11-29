package com.zoo.autotft.domain;

import com.zoo.autotft.domain.synergy.ChampionSynergy;
import java.util.List;

public record Champion(String name, List<ChampionSynergy> synergies) {
}
