package com.rapid.stock.mapper;

import com.rapid.stock.dto.ParentProductSaveRequest;
import com.rapid.stock.dto.ProductAvailabilityDTO;
import com.rapid.stock.dto.ProductVersionSaveRequest;
import com.rapid.stock.model.*;
import com.rapid.stock.repository.OptionCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ParentProductMapper {

       private final MongoTemplate mongoTemplate;

       public ParentProduct mapSaveRequest(ParentProductSaveRequest ppSaveRequest){
              return ParentProduct.builder()
                          .productId(ppSaveRequest.getProductId())
                          .productName(ppSaveRequest.getProductName())
                          .productDescription(ppSaveRequest.getProductDescription())
                          .productVersions(mapToProductVersion(ppSaveRequest.getProductVersions()))
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
                                              .productAvailabilities( mapToProductAvailabilityList(pvSaveRequest.getProductAvailabilitiesDto()) )
                                              .build();})
                                      .collect(Collectors.toList());
       }

       public List<ProductAvailability> mapToProductAvailabilityList(List<ProductAvailabilityDTO> productAvailabilityDTOList){
              return productAvailabilityDTOList.stream()
                                               .map(prodAvailDto ->
                                                        ProductAvailability.builder()
                                                        .companySiteID(prodAvailDto.getCompanySiteID())
                                                        .quantityAvailable(prodAvailDto.getQuantityAvailable())
                                                        .createdAt(LocalDateTime.now())
                                                        .build())
                                               .collect(Collectors.toList());
       }


       public List<OptionCategory> getOptionCategories(List<String> optionCategoryIds){
              return optionCategoryIds.stream()
                                      .map(optionCategoryId -> mongoTemplate.findById(optionCategoryId, OptionCategory.class))
                                      .collect(Collectors.toList());
       }
}
