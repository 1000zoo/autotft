package com.zoo.autotft.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoo.autotft.domain.Champion;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChampionJsonRepository implements JsonRepository<Champion> {
    private final static ObjectMapper mapper = new ObjectMapper();
    private final static String CHAMPION_PATH = "src/main/resources/json/champion_data.json";

    private final Map<String, Champion> repository;
    private final Map<String, List<String>> synergiesNameMap;

    public ChampionJsonRepository() {
        repository = new HashMap<>();
        synergiesNameMap = new HashMap<>();
        initRepository();
    }

    private void initRepository() {
        try {
            JsonNode rootNode = mapper.readTree(new File(CHAMPION_PATH));

            for (Map.Entry<String, JsonNode> property : rootNode.properties()) {
                putChampion(property.getValue());
                putSynergies(property.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putChampion(JsonNode property) {
        String name = property.path("name").textValue();
        int cost = property.path("cost").asInt();
        Champion champion = new Champion(name, cost);

        repository.put(name, champion);
    }

    private void putSynergies(JsonNode property) {
        List<String> synergyNames = convertJsonNode(property.path("synergies"));
        synergiesNameMap.put(property.path("name").textValue(), synergyNames);
    }

    private List<String> convertJsonNode(JsonNode jsonNode) {
        return mapper.convertValue(jsonNode, new TypeReference<>() {
        });
    }

    @Override
    public Champion findByName(String name) {
        if (!repository.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return repository.get(name);
    }

    public List<Champion> findAllList() {
        return new ArrayList<>(repository.values());
    }
}
