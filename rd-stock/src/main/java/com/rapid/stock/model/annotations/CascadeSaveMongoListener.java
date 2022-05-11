package com.rapid.stock.model.annotations;

import com.rapid.stock.model.Rack;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CascadeSaveMongoListener extends AbstractMongoEventListener {

       private final MongoTemplate mongoTemplate;

       public void onAfterSave(AfterSaveEvent event){

              Object superiorSource = event.getSource();

              ReflectionUtils.doWithFields(superiorSource.getClass(), superiorField -> {

                     ReflectionUtils.makeAccessible(superiorField);

                     if (superiorField.isAnnotationPresent(DocumentReference.class) &&
                         superiorField.isAnnotationPresent(CascadeSaveCollection.class)){

                         Object childSource = superiorField.get(superiorSource);

                         if (childSource != null) {

                             if (isCollection(childSource)) {
                                 List objectList = new ArrayList<>((Collection<?>) childSource);
                                 List ids = getAllIdsFromList(objectList);
                                 Class targetClass = objectList.stream().findAny().get().getClass();
                                 String targetField = getTargetField(targetClass, superiorSource.getClass());

                                 ids.stream().forEach(id -> {
                                     mongoTemplate.update(targetClass)
                                             .matching(Criteria.where("id").is(id))
                                             .apply(new Update().push(targetField).value(superiorSource))
                                             .first();
                                 });
                             }
                         }
                     }
              });
       }

       private boolean isCollection(Object object){
               return object.getClass().isArray() || object instanceof Collection;
       }

       private String getTargetField(Class targetClass, Class superiorClass){
               return Arrays.stream(targetClass.getDeclaredFields())
                            .filter(field -> field.getGenericType().getTypeName().contains(superiorClass.getTypeName()))
                            .map(field -> field.getName())
                            .findFirst()
                            .get();
       }

       private List<String> getAllIdsFromList(List list){

           List<String> ids = new ArrayList<>();

           list.forEach(childObj -> {
                   for (Field field : childObj.getClass().getDeclaredFields()) {
                       field.setAccessible(true);

                       if (field.isAnnotationPresent(Id.class) && field.getName().equals("id")) {
                           try {
                               ids.add( (String) field.get(childObj) );
                           } catch (IllegalAccessException e) {
                               e.printStackTrace();
                           }
                       }
                   }
               });

           return ids;
       }
}
