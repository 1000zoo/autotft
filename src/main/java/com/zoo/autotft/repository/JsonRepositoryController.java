package com.zoo.autotft.repository;

import com.zoo.autotft.domain.Champion;
import com.zoo.autotft.domain.synergy.ChampionSynergy;
import com.zoo.autotft.domain.synergy.Synergy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonRepositoryController {

    private static JsonRepositoryController instance;

    private final ChampionJsonRepository championRepository = new ChampionJsonRepository();
    private final SynergyJsonRepository synergyRepository = new SynergyJsonRepository();

    private JsonRepositoryController() {
    }

    public static ChampionJsonRepository getChampionRepository() {
        JsonRepositoryController controller = loadRepository();
        return controller.championRepository;
    }

    public static SynergyJsonRepository getSynergyRepository() {
        JsonRepositoryController controller = loadRepository();
        return controller.synergyRepository;
    }

    private static JsonRepositoryController loadRepository() {
        if (instance != null) {
            return instance;
        }
        instance = new JsonRepositoryController();
        instance.setLinks();

        return instance;
    }

    private void setLinks() {
        Map<Synergy, List<ChampionSynergy>> synergyListMap = new HashMap<>();
        for (Champion champion : championRepository.getAllList()) {
            List<ChampionSynergy> championSynergies = new ArrayList<>();
            for (String synergyName : championRepository.findSynergiesByName(champion.getName())) {
                Synergy synergy = synergyRepository.findByName(synergyName);
                ChampionSynergy championSynergy = new ChampionSynergy(champion, synergy);
                championSynergies.add(championSynergy);

                if (!synergyListMap.containsKey(synergy)) {
                    synergyListMap.put(synergy, new ArrayList<>());
                }
                synergyListMap.get(synergy).add(championSynergy);
            }
            champion.setSynergies(championSynergies);
        }

        for (Map.Entry<Synergy, List<ChampionSynergy>> entry : synergyListMap.entrySet()) {
            entry.getKey().setChampions(entry.getValue());
        }
    }
}
