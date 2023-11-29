package com.zoo.autotft.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.domain.synergy.Synergy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SynergyJsonRepositoryTest {

    private static SynergyJsonRepository repository;

    @BeforeEach
    void setUp() {
        repository = new SynergyJsonRepository();
    }

    @ParameterizedTest
    @DisplayName("시너지 이름으로 엔티티를 불러온다.")
    @ValueSource(strings = {"컨트리", "Heartsteel"})
    void findByNameTest(String name) {
        // given
        Synergy synergy = repository.findByName(name);
        // when
        String resultName = synergy.getName();
        // then
        assertThat(resultName).isEqualTo(name);
    }

    @ParameterizedTest
    @DisplayName("잘못된 이름이라면, 에러를 발생한다.")
    @ValueSource(strings = {"Country", "하트스틸", "", " "})
    void throwIfInvalidName(String name) {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> repository.findByName(name)
        );
    }
}