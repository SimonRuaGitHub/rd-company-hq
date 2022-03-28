package com.rapid.stock;

import com.rapid.stock.model.*;
import com.rapid.stock.repository.ParentProductRepository;
import com.rapid.stock.repository.OptionCategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
public class RapidStockApplication {
    public static void main(String[] args){
        SpringApplication.run(RapidStockApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ParentProductRepository parentProdRepository, OptionCategoryRepository optionCategoryRepository, MongoTemplate mongoTemplate){
           return args -> {};
    }
}
