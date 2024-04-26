package com.rapid.stock.mapper.v2.request;


import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.mapper.v2.CommonMapper;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ProductAvailabilityMapperSaveRequest implements MapperRequest<Availability, AvailabilitySaveRequest> {

    private final ProductVersionRepository productVersionRepository;
    private final CommonMapper commonMapper;

    public Availability mapToEntity(AvailabilitySaveRequest availabilitySaveRequest) {
          return Availability.builder()
                  .id(UUID.randomUUID().toString())
                  .companySiteID(availabilitySaveRequest.getCompanySiteID())
                  .quantityAvailable(availabilitySaveRequest.getQuantityAvailable())
                  .createdAt(LocalDateTime.now())
                  .productVersion( getProductVersion( availabilitySaveRequest.getProductVersionId() ) )
                  .build();
    }

    private ProductVersion getProductVersion(Long productVersionId) {
        return commonMapper.mapToEntityById( productVersionId, productVersionRepository);
    }
}
