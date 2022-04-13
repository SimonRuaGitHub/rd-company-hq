package com.rappid.company.config;

import com.rappid.company.model.Category;
import com.rappid.company.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("local-dev")
public class ConfigLocalInitDataMongo {

    @Bean
    public CommandLineRunner runner(CategoryRepository categoryRepository, MongoTemplate mongoTemplate){
        return args -> {
            mongoTemplate.getCollectionNames().stream().forEach(collection -> mongoTemplate.dropCollection(collection));
            List<Category> categories = Arrays.asList(new Category("6254f54aba7bc1474cb83c36","RESTAURANT"), new Category("6254f54aba7bc1474cb83c37","STORE"));
            categoryRepository.saveAll(categories);
        };
    }
}
