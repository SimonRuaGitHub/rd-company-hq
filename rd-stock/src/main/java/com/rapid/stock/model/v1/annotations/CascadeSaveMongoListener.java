package com.rapid.stock.model.v1.annotations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;


@Component
@RequiredArgsConstructor
public class CascadeSaveMongoListener extends AbstractMongoEventListener {

    private final MongoTemplate mongoTemplate;

    public void onAfterSave(AfterSaveEvent event){
           Object superiorSource = event.getSource();
           ReflectionUtils.doWithFields(superiorSource.getClass(), new CascadeSaveFieldCallBack(mongoTemplate, superiorSource));
    }

}
