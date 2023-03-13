package com.rapid.stock.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
public class Util {

    public List<Long> parseStringListToLong(List<String> values){

           if(values != null && !values.isEmpty())
               try {
                   return values.stream()
                           .map(Long::parseLong)
                           .collect(Collectors.toList());
               }catch(NumberFormatException nfe) {
                   throw new NumberFormatException("The input is invalid to be parsed to a numeric format");
               }
           else
               return null;
    }
}
