package com.rapid.stock.mapper.v2;

import com.rapid.stock.exception.NotFoundException;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Profile("rational-db")
public class MapperList {

    public <T> List<T> mapToEntitiesByIds(List<Long> ids, JpaRepository repository) {
        if (ids != null && !ids.isEmpty()){
            System.out.println("Ids: "+ids);

            return ids.stream().map(id -> {
                   Optional optObject = repository.findById(id);

                   if(optObject.isEmpty())
                       throw new NotFoundException("Entity id wasn't found: "+id);
                   else
                       return (T) optObject.get();
            }).collect(Collectors.toList());

        } else {
            return null;
        }
    }
}
