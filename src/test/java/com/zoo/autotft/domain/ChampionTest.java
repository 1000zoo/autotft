package com.zoo.autotft.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.zoo.autotft.domain.synergy.ActivateConditions;
import com.zoo.autotft.domain.synergy.ChampionSynergy;
import com.zoo.autotft.domain.synergy.Class;
import com.zoo.autotft.domain.synergy.Origin;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ChampionTest {
    private final static ActivateConditions empty = new ActivateConditions(Collections.emptyList());
    private static Champion champion;

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                arguments(new Class("거물", empty), true),
                arguments(new Origin("마에스트로", empty), true),
                arguments(new Origin("거물", empty), false),
                arguments(new Class("감시자", empty), false)
        );
    }

    @BeforeEach
    void setUp() {
        champion = new Champion("진", 5);
        Synergy s1 = new Class("거물", empty);
        Synergy s2 = new Origin("마에스트로", empty);
        ChampionSynergy cs1 = new ChampionSynergy(champion, s1);
        ChampionSynergy cs2 = new ChampionSynergy(champion, s2);
        champion.setSynergies(List.of(cs1, cs2));
    }

    @ParameterizedTest
    @DisplayName("해당 시너지를 가지고 있는 지 확인")
    @MethodSource("getArguments")
    void containsSynergyTest(Synergy synergy, boolean answer) {
        boolean result = champion.containsSynergy(synergy);

        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("관계가 설정되기 전 테스트")
    void beforeLink() {
        Champion champion = new Champion("진", 5);
        Synergy synergy = new Class("거물", empty);

        assertThat(champion.containsSynergy(synergy)).isEqualTo(false);
    }
}