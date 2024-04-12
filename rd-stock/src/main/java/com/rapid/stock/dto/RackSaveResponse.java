package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RackSaveResponse {
    private Long id;
    private String name;
    private String companyId;
}
