package com.rapid.stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rapid.stock.mapper.v1.conversion.LocalDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Config {
    @Bean
    public MongoCustomConversions customConversions(){
           List<Converter<?,?>> converterList = new ArrayList<>();
           converterList.add(new LocalDateTimeConverter());
           return new MongoCustomConversions(converterList);
    }

    @Bean
    public ObjectMapper mapperJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
