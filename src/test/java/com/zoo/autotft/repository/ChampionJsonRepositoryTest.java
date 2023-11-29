package com.zoo.autotft.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.zoo.autotft.domain.Champion;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ChampionJsonRepositoryTest {

    private static ChampionJsonRepository repository;

    @BeforeEach
    private void setUp() {
        repository = new ChampionJsonRepository();
    }

    @ParameterizedTest
    @DisplayName("시너지의 이름을 통해, 챔피언 정보를 불러온다.")
    @ValueSource(strings = {"나미", "트위스티드 페이트"})
    void findByNameTest(String name) {
        // given
        Champion champion = repository.findByName(name);

        // when
        String resultName = champion.name();

        // then
        assertThat(resultName).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("없는 기물이 들어올 경우, 에러가 발생한다.")
    @ValueSource(strings = {"", " ", "트위스티드페이트"})
    void throwIfInvalidName(String name) {
        assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByName(name)
        );
    }

    @Test
    @DisplayName("모든 시너지 정보를 불러온다.")
    void findAllListTest() {
        List<Champion> list = repository.findAllList();
        assertThat(list.size()).isEqualTo(60);
    }
}