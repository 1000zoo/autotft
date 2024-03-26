package com.zoo.autotft.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.repository.JsonRepositoryController;
import com.zoo.autotft.repository.Repository;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeckTest {

    private static Repository<Champion> championRepository;
    private static Repository<Synergy> synergyRepository;

    @BeforeEach
    void setUp() {
        championRepository = JsonRepositoryController.getChampionRepository();
        synergyRepository = JsonRepositoryController.getSynergyRepository();
    }

    @Test
    @DisplayName("Deck이 잘 만들어지는 지 테스트")
    void createDeckTest() {
        // given
        int level = 10;
        List<Champion> champions = championOf(List.of("나미", "타릭", "케넨"));
        List<Synergy> synergies = synergyOf(List.of("디스코"));

        // when
        Deck deck = new Deck(level, champions, synergies);

        // then
        System.out.println(deck);

    }

    @Test
    @DisplayName("Deck copy 테스트")
    void copyTest() {
        // given
        int level = 10;
        List<Champion> champions = championOf(List.of("나미", "타릭", "케넨"));
        List<Synergy> synergies = synergyOf(List.of("디스코"));
        Deck origin = new Deck(level, champions, synergies);

        // when
        Deck copy = new Deck(origin);
        origin.add(championRepository.findByName("루시안"));

        // then
        System.out.println("origin => " + origin);
        System.out.println("copy => " + copy);

    }

    @Test
    @DisplayName("Deck remove 테스트")
    void removeTest() {
        // given
        int level = 10;
        List<Champion> champions = championOf(List.of("나미", "타릭", "케넨"));
        List<Synergy> synergies = synergyOf(List.of("디스코"));
        Deck deck = new Deck(level, champions, synergies);

        // when
        deck.remove(championRepository.findByName("타릭"));
        int score = deck.score();

        // then
        assertThat(score).isEqualTo(0);

    }

    @Test
    @DisplayName("현재 조합의 시너지와 관련된 챔피언들 목록 불러오기")
    void getAllPossibleChampionsTest() {
        // given
        int level = 10;
        List<Champion> champions = championOf(List.of("나미", "타릭", "케넨"));
        List<Synergy> synergies = synergyOf(List.of("디스코"));
        Deck deck = new Deck(level, champions, synergies);

        // when
        List<Champion> allPossibleChampions = deck.getAllPossibleChampions();

        // then
        System.out.println(allPossibleChampions);

    }

    private List<Synergy> synergyOf(List<String> names) {
        return names.stream().map(synergyRepository::findByName).collect(Collectors.toList());
    }

    private List<Champion> championOf(List<String> names) {
        return names.stream().map(championRepository::findByName).collect(Collectors.toList());
    }
}