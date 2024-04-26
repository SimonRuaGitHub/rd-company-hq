package com.rapid.stock.dto.v2;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ParentProductSaveResponse {
    private Long id;
    private String name;
}
