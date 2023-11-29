package com.zoo.autotft.repository;

import com.zoo.autotft.domain.synergy.Synergy;
import java.util.HashMap;
import java.util.Map;

public class SynergyJsonRepository implements JsonRepository<Synergy> {
    private final static String CLASS_PATH = "src/main/resources/json/champion_data.json";
    private final static String ORIGIN_PATH = "src/main/resources/json/champion_data.json";

    private final Map<String, Synergy> repository;

    public SynergyJsonRepository() {
        repository = new HashMap<>();
        initRepository();
    }

    private void initRepository() {
        initRepository(CLASS_PATH);
        initRepository(ORIGIN_PATH);
    }

    private void initRepository(String path) {

    }

    @Override
    public Synergy findByName(String name) {
        return null;
    }
}
