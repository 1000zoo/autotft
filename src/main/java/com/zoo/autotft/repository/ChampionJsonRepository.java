package com.zoo.autotft.repository;

import com.zoo.autotft.domain.Champion;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionJsonRepository implements JsonRepository<Champion> {
    private final static String CHAMPION_PATH = "src/main/resources/json/champion_data.json";

    private final Map<String, Champion> repository;

    public ChampionJsonRepository() {
        repository = new HashMap<>();
    }

    @Override
    public Champion findByName(String name) {
        return null;
    }

    public List<Champion> findAllList() {
        return null;
    }
}
