package com.rappid.company.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RestFieldErrors {
    List<String> errorMessages;
}
