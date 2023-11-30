package com.zoo.autotft.domain.synergy;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.domain.Champion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SynergyTest {

    private static Synergy synergy;
    private static final ActivateConditions empty = new ActivateConditions(Collections.emptyList());

    @BeforeEach
    void setUp() {
        synergy = new Class("이모코어", empty);
        ChampionSynergy cs1 = new ChampionSynergy(new Champion("애니", 1), synergy);
        ChampionSynergy cs2 = new ChampionSynergy(new Champion("백스", 3), synergy);
        ChampionSynergy cs3 = new ChampionSynergy(new Champion("아무무", 3), synergy);
        ChampionSynergy cs4 = new ChampionSynergy(new Champion("뽀삐", 4), synergy);

        synergy.setChampions(List.of(cs1, cs2, cs3, cs4));
    }

    @ParameterizedTest
    @DisplayName("기물 이름이 주어지면, 시너지에 해당되는 지 확인")
    @CsvSource(value = {
            "애니,1,true",
            "백스,3,true",
            "아무무,3,true",
            "뽀삐,4,true",
            "진,5,false",
            "루시안,5,false"
    })
    void containsChampionTest(String championName, int cost, boolean answer) {
        //given
        Champion champion = new Champion(championName, cost);

        //when
        boolean result = synergy.containsChampion(champion);

        //then
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("관계가 설정되기 전 테스트")
    void beforeLink() {
        //given
        Synergy synergy = new Class("이모코어", empty);
        Champion champion = new Champion("애니", 1);

        //when
        boolean result = synergy.containsChampion(champion);

        //then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("equals: 같은 이름이라면, 같은 시너지이다.")
    void equalsCaseSameName() {
        // given
        Synergy synergy1 = new Class("난동꾼", empty);
        Synergy synergy2 = new Class("난동꾼", empty);

        // when
        boolean result = synergy1.equals(synergy2);

        // then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("equals: 같은 이름이라도, 다른 타입이라면, 다른 시너지이다.")
    void equalsCaseDifferentType() {
        //given
        Synergy synergy1 = new Class("난동꾼", empty);
        Synergy synergy2 = new Origin("난동꾼", empty);

        //when
        boolean result = synergy1.equals(synergy2);

        //then
        assertThat(result).isEqualTo(false);
    }

    @Test
    @DisplayName("다른 레퍼런스를 가지고 있더라도, 이름이 같다면, contains로 찾을 수 있다.")
    void containsTest() {
        //given
        Synergy synergy1 = new Class("난동꾼", empty);
        Synergy synergy2 = new Class("난동꾼", empty);

        List<Synergy> synergyList = new ArrayList<>();
        synergyList.add(synergy1);

        //when
        boolean result = synergyList.contains(synergy2);

        //then
        assertThat(result).isEqualTo(true);
    }
}