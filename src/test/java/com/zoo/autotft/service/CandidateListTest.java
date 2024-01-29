package com.zoo.autotft.service;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.Deck;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.repository.JsonRepositoryController;
import com.zoo.autotft.repository.Repository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CandidateListTest {

    private CandidateList candidateList;

    private Repository<Champion> championRepository;
    private Repository<Synergy> synergyRepository;

    @BeforeEach
    void setUp() {
        candidateList = new CandidateList();
        championRepository = JsonRepositoryController.getChampionRepository();
        synergyRepository = JsonRepositoryController.getSynergyRepository();
    }

    @Test
    @DisplayName("Deck 이 score 기준 오름차순으로 등록되는 지 테스트")
    void deckPriorityTest() {
        // given
        Deck deck1 = deckCreator();
        Deck deck2 = new Deck(deck1);
        deck2.add(championRepository.findByName("야스오"));

        // when
        candidateList.add(deck2);   // 디스코3, 수호자2, True Damage2 => 3
        candidateList.add(deck1);   // 디스코3, 수호자2 => 2

        // then
        System.out.println(candidateList);

    }

    private Deck deckCreator() {
        List<Champion> champions = List.of(
                championRepository.findByName("타릭"),
                championRepository.findByName("나미"),
                championRepository.findByName("케넨")
        );
        List<Synergy> synergies = List.of(
                synergyRepository.findByName("디스코")
        );

        return new Deck(10, champions, synergies);
    }
}