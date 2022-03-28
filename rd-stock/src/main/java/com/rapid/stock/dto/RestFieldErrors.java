package com.rapid.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RestFieldErrors {
    private List<String> errorMessages;
}
