package com.zoo.autotft.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.ActivateConditions;
import com.zoo.autotft.domain.synergy.Class;
import com.zoo.autotft.domain.synergy.Origin;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class JsonRepositoryControllerTest {

    private final static ActivateConditions empty = new ActivateConditions(Collections.emptyList());

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
    @DisplayName("기물과 시너지가 잘 연결되었는 지 테스트")
    @CsvSource(value = {
            "진,거물,true",
            "진,마에스트로,true",
            "진,난동꾼,false",
            "세트,난동꾼,true",
            "세트,춤꾼,true",
            "세트,Heartsteel,true",
            "세트,마에스트로,false",
    })
    void championRepositoryTest(String championName, String synergyName, boolean answer) {
        //given
        Champion champion = championRepository.findByName(championName);
        Synergy synergy = synergyRepository.findByName(synergyName);

        //when
        boolean championContainsSynergy = champion.containsSynergy(synergy);
        boolean synergyContainsChampion = synergy.containsChampion(champion);

        //then
        assertThat(championContainsSynergy).isEqualTo(answer);
        assertThat(synergyContainsChampion).isEqualTo(answer);
    }
}