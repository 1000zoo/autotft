package com.zoo.autotft.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.repository.ChampionJsonRepository;
import com.zoo.autotft.repository.JsonRepositoryController;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class ChampionComparableTest {

    private static ChampionJsonRepository championJsonRepository;

    @BeforeEach
    private void setUp() {
        championJsonRepository = JsonRepositoryController.getChampionRepository();
    }


    @ParameterizedTest
    @DisplayName("정렬기준 테스트")
    @CsvSource(value = {
            "소나,진",   //소나 (5) < 진 (5)
            "코르키,세트",  //코르키 (1) < 세트 (3)
    }, delimiter = ',')
    void compareToTest(String championName1, String championName2) {
        //given
        Champion smaller = championJsonRepository.findByName(championName1);
        Champion bigger = championJsonRepository.findByName(championName2);

        //when
        boolean result = smaller.compareTo(bigger) < 0;

        //then
        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("실제 정렬 테스트")
    void sortTest() {
        //given
        String first = "코르키";
        String second = "세트";
        String third = "소나";
        String forth = "진";

        List<Champion> answer = new ArrayList<>(List.of(
                championJsonRepository.findByName(first),
                championJsonRepository.findByName(second),
                championJsonRepository.findByName(third),
                championJsonRepository.findByName(forth)
        ));

        //when
        List<Champion> championList = new ArrayList<>(List.of(
                championJsonRepository.findByName(third),
                championJsonRepository.findByName(first),
                championJsonRepository.findByName(forth),
                championJsonRepository.findByName(second)
        ));

        Collections.sort(championList);

        //then
        for (int i = 0; i < championList.size(); i++) {
            assertThat(championList.get(i)).isEqualTo(answer.get(i));
        }
    }
}
