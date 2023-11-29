package com.zoo.autotft.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zoo.autotft.domain.synergy.ActivateConditions;
import com.zoo.autotft.domain.synergy.Class;
import com.zoo.autotft.domain.synergy.Origin;
import com.zoo.autotft.domain.synergy.Synergy;
import com.zoo.autotft.util.ConstructorInterface;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SynergyJsonRepository implements JsonRepository<Synergy> {
    private final static String CLASS_PATH = "src/main/resources/json/class_data.json";
    private final static String ORIGIN_PATH = "src/main/resources/json/origin_data.json";

    private final Map<String, Synergy> repository;

    public SynergyJsonRepository() {
        repository = new HashMap<>();
        initRepository();
    }

    private void initRepository() {
        initRepository(CLASS_PATH, Class::new);
        initRepository(ORIGIN_PATH, Origin::new);
    }

    private void initRepository(String path, ConstructorInterface<String, ActivateConditions, Synergy> constructor) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(path));

            for (Map.Entry<String, JsonNode> property : rootNode.properties()) {
                JsonNode name = property.getValue().path("name");
                JsonNode activeJson = property.getValue().path("active");
                ActivateConditions conditions = new ActivateConditions(
                        stringListToIntegerList(convertJsonNode(mapper, activeJson)));
                repository.put(name.textValue(), constructor.from(name.textValue(), conditions));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> convertJsonNode(ObjectMapper mapper, JsonNode jsonNode) {
        return mapper.convertValue(jsonNode, new TypeReference<>() {
        });
    }

    private List<Integer> stringListToIntegerList(List<String> list) {
        return list.stream().mapToInt(Integer::parseInt).boxed().toList();
    }

    @Override
    public Synergy findByName(String name) {
        if (!repository.containsKey(name)) {
            throw new IllegalArgumentException("[Error] json 파일 손상");
        }
        return repository.get(name);
    }
}
