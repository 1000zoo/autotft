package com.zoo.autotft.dto;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.List;
import java.util.Map;

public record RecommendDeckDto(List<Champion> champions, Map<Synergy, Integer> activations) {
}
