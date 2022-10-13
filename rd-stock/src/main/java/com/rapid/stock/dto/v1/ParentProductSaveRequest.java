package com.rapid.stock.dto.v1;

import com.rapid.stock.dto.ProductVersionSaveRequest;
import lombok.Data;
import java.util.List;

@Data
public class ParentProductSaveRequest {
       private String productId;
       private String productName;
       private String productDescription;
       private List<ProductVersionSaveRequest> productVersions;
       private List<String> rackIds;
}
