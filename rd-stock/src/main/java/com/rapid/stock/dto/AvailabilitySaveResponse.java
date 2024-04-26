package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AvailabilitySaveResponse {
    private UUID availabilityId;
}
