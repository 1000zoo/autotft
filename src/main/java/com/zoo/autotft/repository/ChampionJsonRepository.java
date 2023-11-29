package com.zoo.autotft.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoo.autotft.domain.Champion;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionJsonRepository implements JsonRepository<Champion> {
    private final static String CHAMPION_PATH = "src/main/resources/json/champion_data.json";

    private final Map<String, Champion> repository;

    public ChampionJsonRepository() {
        repository = new HashMap<>();
        initRepository();
    }

    private void initRepository() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(CHAMPION_PATH));

            for (Map.Entry<String, JsonNode> field : rootNode.properties()) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Champion findByName(String name) {
        return null;
    }

    public List<Champion> findAllList() {
        return null;
    }
}
