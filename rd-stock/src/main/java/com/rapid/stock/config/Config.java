package com.rapid.stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
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
           return new ObjectMapper();
    }
}
