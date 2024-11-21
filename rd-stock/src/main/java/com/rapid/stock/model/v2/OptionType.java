package com.rapid.stock.model.v2;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum OptionType {

    BASE_PRODUCT("base_product"), PACKAGE("package"), ADDITION("addition");

    private final String value;

    OptionType(String value) {
        this.value = value;
    }

    public static OptionType findByValue(String value) {
        return Arrays.stream(OptionType.values())
                .filter(optType -> optType.getValue().equals(value))
                .findFirst()
                .orElseThrow(
                        () -> new RuntimeException(
                                "The option type value: "+ value + " doesn't match any of the existing strings"
                        )
                );
    }
}