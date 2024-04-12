package com.rapid.stock.dto;

import lombok.Data;
import java.util.List;

@Data
public class RackSaveRequest {
    private String name;
    private String description;
    private List<String> productIds;
    private List<String> racksIds;
    private String companyId;
    private Long parentRackId;
}
