package com.zoo.autotft.domain.synergy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.repository.ChampionJsonRepository;
import com.zoo.autotft.repository.JsonRepositoryController;
import com.zoo.autotft.repository.SynergyJsonRepository;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

class SynergyStatusTest {

    private static SynergyJsonRepository synergyRepository;
    private static ChampionJsonRepository championRepository;
    private static SynergyStatus status;

    @BeforeEach
    void setUp() {
        status = new SynergyStatus();
        synergyRepository = JsonRepositoryController.getSynergyRepository();
        championRepository = JsonRepositoryController.getChampionRepository();
    }

    @Test
    @DisplayName("복사 생성자 테스트: 복사받은 객체는, 복사한 객체의 변화에 영향이 없어야한다.")
    void copyConstructorTest() {
        // given
        Synergy synergy = synergyRepository.findByName("디스코");
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
        Synergy synergy1 = synergyRepository.findByName("디스코");
        Synergy synergy2 = synergyRepository.findByName("KDA");
        Synergy synergy3 = synergyRepository.findByName("컨트리");
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
        Synergy synergy1 = synergyRepository.findByName("디스코");
        status.put(synergy1);

        assertThat(status.remove(synergy1)).isEqualTo(true); // "디스코"가 1개 제거되고, key 자체가 사라진다.
        assertThat(status.remove(synergy1)).isEqualTo(false); // "디스코" key 가 위에서 제거되었으므로, false 를 반환한다.
    }

    @Test
    @DisplayName("remove 내부 자세히 테스트")
    void removeDetailTest() {
        // given
        status.put(championRepository.findByName("타릭"));
        status.put(championRepository.findByName("나미"));
        status.put(championRepository.findByName("케넨"));
        status.put(synergyRepository.findByName("디스코"));

        // when
        status.remove(synergyRepository.findByName("디스코"));
        status.remove(synergyRepository.findByName("수호자"));

        // then
        assertThat(status.score()).isEqualTo(0);
    }

    @Test
    @DisplayName("champion put 테스트")
    void putChampionTest() {
        // given
        Champion champion = championRepository.findByName("나미");    // 디스코, 현혹술사

        // when
        status.put(champion);

        // then
        assertThat(status.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("활성화되는 시너지 수 구하는 기능 테스트")
    void scoreTest() {
        // given
        SynergyStatus status = new SynergyStatus();
        status.put(synergyRepository.findByName("디스코"));
        status.put(synergyRepository.findByName("디스코"));
        status.put(synergyRepository.findByName("디스코"));    // 시너지 한 개 활성
        status.put(synergyRepository.findByName("수호자"));
        status.put(synergyRepository.findByName("수호자"));    // 시너지 두 개 활성
        status.put(synergyRepository.findByName("하이퍼팝"));   // 시너지 세 개 활성

        // when
        int score = status.score();

        // then
        assertThat(score).isEqualTo(3);

    }

    @Test
    @DisplayName("SynergyStatus 정렬 테스트")
    void sortTest() {
        // given
        SynergyStatus status = new SynergyStatus();
        status.put(championRepository.findByName("케인"));
        status.put(championRepository.findByName("케일"));
        status.put(championRepository.findByName("비에고"));
        status.put(championRepository.findByName("나르"));

        // when
        Map<Synergy, Integer> results = status.getSortedStatus();

        // then
        for (Synergy synergy : results.keySet()) {
            System.out.println(synergy.name + ": " + results.get(synergy));
        }

    }
}