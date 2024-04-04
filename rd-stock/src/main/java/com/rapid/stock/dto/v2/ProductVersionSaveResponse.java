package com.rapid.stock.dto.v2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductVersionSaveResponse {
    private UUID versionId;
}
