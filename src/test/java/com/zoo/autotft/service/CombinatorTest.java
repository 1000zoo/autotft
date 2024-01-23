package com.zoo.autotft.service;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CombinatorTest {

    @Test
    @DisplayName("총 기물 수, 선택된 기물의 이름들, 추가 활성 시너지 목록을 가지고 조합을 완성한다.")
    void combinatorTdd() {
        // given
        int maximumNumber = 10;
        List<String> championNames = List.of("나미", "타릭", "케넨");
        List<String> synergyNames = List.of("디스코"); // 타릭이 헤드라이너인 상황

        // when
        Combinator combinator = new Combinator();
        List<String> combinedList = combinator.combine(maximumNumber, championNames, synergyNames);

        // then
        Assertions.assertThat(combinedList.size()).isEqualTo(maximumNumber);

    }
}