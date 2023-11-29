package com.zoo.autotft.repository;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.ActivateConditions;
import com.zoo.autotft.domain.synergy.Class;
import com.zoo.autotft.domain.synergy.Origin;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.Collections;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class JsonRepositoryControllerTest {

    private final static ActivateConditions empty = new ActivateConditions(Collections.emptyList());

    private static Stream<Arguments> getChampionTestArguments() {
        return Stream.of(
                arguments("진", getSynergyClass("거물"), true),
                arguments("진", getSynergyOrigin("마에스트로"), true),
                arguments("진", getSynergyClass("마에스트로"), false),
                arguments("진", getSynergyOrigin("난동꾼"), false),
                arguments("세트", getSynergyClass("난동꾼"), true),
                arguments("세트", getSynergyClass("춤꾼"), true),
                arguments("세트", getSynergyOrigin("Heartsteel"), true),
                arguments("세트", getSynergyOrigin("처형자"), false)
        );
    }

    private static Synergy getSynergyClass(String name) {
        return new Class(name, empty);
    }

    private static Synergy getSynergyOrigin(String name) {
        return new Origin(name, empty);
    }

    private Repository<Champion> championRepository;
    private Repository<Synergy> synergyRepository;

    @BeforeEach
    private void setUp() {
        championRepository = JsonRepositoryController.getChampionRepository();
        synergyRepository = JsonRepositoryController.getSynergyRepository();
    }

    @ParameterizedTest
    @DisplayName("기물 이름으로 시너지 목록 불러오기")
    @MethodSource("getChampionTestArguments")
    void championRepositoryTest(String championName, Synergy synergy, boolean answer) {
        //given
        Champion champion = championRepository.findByName(championName);

        //when
        boolean result = champion.containsSynergy(synergy);

        //then
//        assertThat(result).isEqualTo(answer);
        System.out.println(champion.getName());
        System.out.println(champion.getSynergies());
    }
}