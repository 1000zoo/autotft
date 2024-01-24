package com.zoo.autotft.domain.synergy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.zoo.autotft.repository.JsonRepositoryController;
import com.zoo.autotft.repository.SynergyJsonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class SynergyStatusTest {

    private static SynergyJsonRepository repository;
    private static SynergyStatus status;

    @BeforeEach
    void setUp() {
        status = new SynergyStatus();
        repository = JsonRepositoryController.getSynergyRepository();
    }

    @Test
    @DisplayName("복사 생성자 테스트: 복사받은 객체는, 복사한 객체의 변화에 영향이 없어야한다.")
    void copyConstructorTest() {
        // given
        Synergy synergy = repository.findByName("디스코");
        status.put(synergy);

        // when
        SynergyStatus copy = new SynergyStatus(status);
        status.put(synergy);

        // then
        assertThrows(
                AssertionFailedError.class,
                () -> assertThat(copy.size()).isEqualTo(status.size())
        );
    }

    @Test
    @DisplayName("size 메서드 테스트")
    void sizeTest() {
        // given
        Synergy synergy1 = repository.findByName("디스코");
        Synergy synergy2 = repository.findByName("KDA");
        Synergy synergy3 = repository.findByName("컨트리");
        status.put(synergy1);
        status.put(synergy1);
        status.put(synergy2);
        status.put(synergy2);
        status.put(synergy2);
        status.put(synergy3);

        // when
        int size = status.size();

        //then
        assertThat(size).isEqualTo(6);
    }

    @Test
    @DisplayName("remove 테스트")
    void removeTest() {
        Synergy synergy1 = repository.findByName("디스코");
        status.put(synergy1);

        assertThat(status.remove(synergy1)).isEqualTo(true); // "디스코"가 1개 제거되고, key 자체가 사라진다.
        assertThat(status.remove(synergy1)).isEqualTo(false); // "디스코" key 가 위에서 제거되었으므로, false 를 반환한다.
    }
}