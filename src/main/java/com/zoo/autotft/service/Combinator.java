package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.List;

public interface Combinator {
    List<Deck> combine(int maximumNumber, List<Champion> champions, List<Synergy> synergies);
}
