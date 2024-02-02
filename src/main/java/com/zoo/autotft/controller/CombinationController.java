package com.zoo.autotft.controller;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.dto.RecommendDeckDto;
import com.zoo.autotft.repository.JsonRepositoryController;
import com.zoo.autotft.repository.Repository;
import com.zoo.autotft.service.BasicCombinator;
import com.zoo.autotft.service.Combinator;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CombinationController {

    private final Repository<Champion> championRepository = JsonRepositoryController.getChampionRepository();
    private final Repository<Synergy> synergyRepository = JsonRepositoryController.getSynergyRepository();

    @GetMapping("/combinator")
    public String showCombinatorPage(Model model) {
        model.addAttribute("tftForm", new TftSelectionForm());
        model.addAttribute("champions", championRepository.getAllList());
        model.addAttribute("synergies", synergyRepository.getAllList());
        return "combinator/combinatorMain";
    }

    @PostMapping("/combinator")
    public String showResults(@ModelAttribute("tftForm") TftSelectionForm form, Model model) {
        Combinator combinator = new BasicCombinator(championRepository);
        int maxLevel = form.getLevel();
        List<Champion> selectedChampions = createChampionList(form.getSelectedChampions());
        List<Synergy> selectedSynergies = createSynergyList(form.getSelectedSynergies());
        List<RecommendDeckDto> results = combinator.combine(maxLevel, selectedChampions, selectedSynergies);
        model.addAttribute("recommendDecks", results);
        System.out.println(results);
        return "combinator/combinatorResults";
    }

    private List<Champion> createChampionList(List<String> names) {
        return names.stream().map(championRepository::findByName).toList();
    }

    private List<Synergy> createSynergyList(List<String> names) {
        return names.stream().map(synergyRepository::findByName).toList();
    }
}
