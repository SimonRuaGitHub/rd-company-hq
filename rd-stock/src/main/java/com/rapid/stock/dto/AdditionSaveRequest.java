package com.rapid.stock.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
public class AdditionSaveRequest {
    private MultipartFile image;
    private AdditionMedataSaveRequest additionMedataSaveRequest;
}
