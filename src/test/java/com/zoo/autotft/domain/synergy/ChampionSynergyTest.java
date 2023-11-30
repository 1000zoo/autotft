package com.zoo.autotft.domain.synergy;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.domain.Champion;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChampionSynergyTest {

    private final static ActivateConditions empty = new ActivateConditions(Collections.emptyList());
    private final static int mockCost = 5;

    private static ChampionSynergy createInstance(String championName, String synergyName) {
        Champion champion = new Champion(championName, mockCost);
        Synergy synergy = new Class(synergyName, empty);
        return new ChampionSynergy(champion, synergy);
    }

    @ParameterizedTest
    @DisplayName("record 의 equals 테스트")
    @CsvSource(value = {
            "진,거물,진,거물,true",
            "진,거물,진,마에스트로,false",
            "진,거물,이즈,거물,false",
    }, delimiter = ',')
    void equalsTest(String champ1, String syn1, String champ2, String syn2, boolean answer) {
        // given
        ChampionSynergy championSynergy1 = createInstance(champ1, syn1);
        ChampionSynergy championSynergy2 = createInstance(champ2, syn2);

        // when
        boolean result = championSynergy1.equals(championSynergy2);

        // then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("hasSameSynergy 테스트")
    void hasSameSynergyTest() {
        // given
        ChampionSynergy championSynergy = createInstance("진", "거물");
        Synergy synergy = new Class("거물", empty);

        // when
        boolean result = championSynergy.hasSameSynergy(synergy);

        // then
        assertThat(result).isEqualTo(true);
    }
}