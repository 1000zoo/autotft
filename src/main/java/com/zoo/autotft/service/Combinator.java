package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.dto.RecommendDeckDto;
import java.util.List;

public interface Combinator {
    List<RecommendDeckDto> combine(int maximumNumber, List<Champion> champions, List<Synergy> synergies);
}
