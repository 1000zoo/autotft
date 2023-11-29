package com.zoo.autotft.domain.synergy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ActivateConditionsTest {

    private static Stream<Arguments> getArguments() {
        return Stream.of(
                arguments(List.of(1, 2, 3, 4), 3, 3),
                arguments(List.of(3, 5, 7, 10), 4, 3),
                arguments(List.of(3, 5, 7, 10), 9, 7),
                arguments(List.of(2, 4, 6), 1, 0)
        );
    }

    @ParameterizedTest
    @DisplayName("시너지 수가 주어지면, 현재 활성화된 시너지 수를 반환한다.")
    @MethodSource("getArguments")
    void getActivateState(List<Integer> conditions, int count, int answer) {
        // given
        ActivateConditions activateConditions = new ActivateConditions(conditions);

        // when
        int actual = activateConditions.getActivatedState(count);

        // then
        assertThat(actual).isEqualTo(answer);
    }
}