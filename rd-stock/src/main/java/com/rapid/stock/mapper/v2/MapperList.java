package com.rapid.stock.mapper.v2;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("rational-db")
public class MapperList {

    public <T> List<T> mapToEntitiesByIds(List<Long> ids, JpaRepository repository) {
        if (ids != null && ids.isEmpty()){
            return repository.findAllById((Iterable<Long>) ids);
        } else {
            return null;
        }
    }
}
