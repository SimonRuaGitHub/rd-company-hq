package com.rapid.stock.model.rules;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GeneralSchemaRules {
    public boolean repeatedIDsInsideCollection(List<String> ids){
           return ids.stream()
                     .distinct()
                     .anyMatch(idToFind -> ids.stream().filter(id -> idToFind.equals(id)).count() > 1);
    }
}
