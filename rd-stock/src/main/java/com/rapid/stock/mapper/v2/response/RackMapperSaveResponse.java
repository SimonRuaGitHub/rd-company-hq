package com.rapid.stock.mapper.v2.response;

import com.rapid.stock.dto.RackSaveResponse;
import com.rapid.stock.dto.v2.ProductVersionSaveResponse;
import com.rapid.stock.model.v2.Rack;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RackMapperSaveResponse {
    public RackSaveResponse map(Rack rack) {
        return RackSaveResponse.builder()
                .id(rack.getId())
                .name(rack.getName())
                .companyId(rack.getCompanyId())
                .build();
    }
}
