package com.rapid.stock.mapper.v2;


import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.ProductTypeRepository;
import com.rapid.stock.repository.v2.ProductVersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Profile("rational-db")
public class ProductAvailabilityMapper {

    private final ProductVersionRepository productVersionRepository;
    private final CommonMapper commonMapper;

    public Availability mapToAvailability(AvailabilityDTO availabilityDTO) {
          return Availability.builder()
                  .id(UUID.randomUUID().toString())
                  .companySiteID(availabilityDTO.getCompanySiteID())
                  .quantityAvailable(availabilityDTO.getQuantityAvailable())
                  .createdAt(LocalDateTime.now())
                  .productVersion( getProductVersion( availabilityDTO.getProductVersionId() ) )
                  .build();
    }

    private ProductVersion getProductVersion(Long productVersionId) {
        return commonMapper.mapToEntityById( productVersionId, productVersionRepository);
    }
}
