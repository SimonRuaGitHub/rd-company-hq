package com.rapid.stock.model.v2;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OptionType {

    BASE_PRODUCT, PACKAGE, ADDITION;

    public static OptionType findByValue(String value) {
        return Arrays.stream(OptionType.values())
                .filter(optType -> optType.name().toLowerCase().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException(
                                "The option type value: "+ value + " doesn't match any of the existing option types"
                        )
                );
    }
}