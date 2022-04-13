package com.rappid.company;

import com.rappid.company.model.Category;
import com.rappid.company.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class RapidCompanyApplication
{
    public static void main(String[] args) {
        SpringApplication.run(RapidCompanyApplication.class, args);
    }
}
