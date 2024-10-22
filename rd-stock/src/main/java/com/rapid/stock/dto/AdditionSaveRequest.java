package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class AdditionSaveRequest {
    private MultipartFile image;
    private AdditionMedataSaveRequest additionMedataSaveRequest;
}
