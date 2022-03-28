package com.rapid.stock.mapper.conversion;


import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@ReadingConverter
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String date) {
           return date == null || date.isBlank() || date.isEmpty() ? null:LocalDateTime.parse(date);
    }
}
