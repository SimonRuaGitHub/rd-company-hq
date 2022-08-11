package com.rapid.stock.mapper.v1;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.dto.ProductVersionSaveRequest;
import com.rapid.stock.exception.DuplicatedReferenceException;
import com.rapid.stock.model.v1.Availability;
import com.rapid.stock.model.v1.OptionCategory;
import com.rapid.stock.model.v1.ParentProduct;
import com.rapid.stock.model.v1.ProductVersion;
import com.rapid.stock.model.v1.rules.GeneralSchemaRules;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Profile("decrapted")
public class ParentProductMapper {

       private final MongoTemplate mongoTemplate;
       private final RackMapperList rackMapperList;
       private final GeneralSchemaRules generalSchemaRules;

       public ParentProduct mapSaveRequest(ParentProductSaveRequest ppSaveRequest){

              if(ppSaveRequest.getRackIds() != null && generalSchemaRules.repeatedIDsInsideCollection(ppSaveRequest.getRackIds()))
                 throw new DuplicatedReferenceException("Racks ids can't be repeated");

              return ParentProduct.builder()
                          .productId(ppSaveRequest.getProductId())
                          .productName(ppSaveRequest.getProductName())
                          .productDescription(ppSaveRequest.getProductDescription())
                          .productVersions(mapToProductVersion(ppSaveRequest.getProductVersions()))
                          .associatedRacks(rackMapperList.mapToRackEntities(ppSaveRequest.getRackIds()))
                          .createdAt(LocalDateTime.now())
                          .build();
       }

       public List<ProductVersion> mapToProductVersion(List<ProductVersionSaveRequest> pvSaveRequestList){

              return pvSaveRequestList.stream()
                                      .map(pvSaveRequest -> { return ProductVersion.builder()
                                              .versionId(UUID.randomUUID().toString())
                                              .name(pvSaveRequest.getName())
                                              .description(pvSaveRequest.getDescription())
                                              .productType(pvSaveRequest.getProductType())
                                              .price(pvSaveRequest.getPrice())
                                              .isAvailable(pvSaveRequest.isAvailable())
                                              .createdAt(LocalDateTime.now())
                                              .optionCategories( getOptionCategories(pvSaveRequest.getOptionCategoriesIds()) )
                                              .productAvailabilities( mapToProductAvailabilityList(pvSaveRequest.getProductAvailabilities()) )
                                              .build();})
                                      .collect(Collectors.toList());
       }

       public List<Availability> mapToProductAvailabilityList(List<AvailabilityDTO> productAvailabilityDTOList){
              if(productAvailabilityDTOList != null && !productAvailabilityDTOList.isEmpty())
                     return productAvailabilityDTOList.stream()
                                                      .map(prodAvailDto ->
                                                               Availability.builder()
                                                               .companySiteID(prodAvailDto.getCompanySiteID())
                                                               .quantityAvailable(prodAvailDto.getQuantityAvailable())
                                                               .createdAt(LocalDateTime.now())
                                                               .build())
                                                      .collect(Collectors.toList());
              else
                     return null;
       }


       public List<OptionCategory> getOptionCategories(List<String> optionCategoryIds){
              if(optionCategoryIds != null && !optionCategoryIds.isEmpty())
                 return optionCategoryIds.stream()
                                      .map(optionCategoryId -> mongoTemplate.findById(optionCategoryId, OptionCategory.class))
                                      .collect(Collectors.toList());
              else
                   return null;
       }

       public List<ParentProduct> mapToParentProductForRacks(List<String> productIds) {
              if (productIds != null && !productIds.isEmpty())
                     return  productIds.stream()
                                        .map(id -> mongoTemplate.findById(id, ParentProduct.class))
                                        .collect(Collectors.toList());
              else
                     return null;
       }
}
