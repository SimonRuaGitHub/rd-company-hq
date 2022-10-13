package com.rapid.stock.dto.v2;

import lombok.Data;

import java.util.List;

@Data
public class ParentProductSaveRequest {
       private String productId;
       private String productName;
       private String productDescription;
       private String companyId;
       private List<String> productVersions;
       private List<String> rackIds;
}
