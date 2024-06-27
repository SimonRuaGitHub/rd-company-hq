package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilitySaveRequest;
import com.rapid.stock.dto.AvailabilitySaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.ProductAvailabilityMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.ProductAvailabilityMapperSaveResponse;
import com.rapid.stock.model.operations.GeneralDeleteOperation;
import com.rapid.stock.model.operations.GeneralSaveOperation;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.repository.v2.ProductAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@AllArgsConstructor
public class ProductAvailabilityServiceImp implements ProductAvailabilityService {

    private final Validator validator;
    private final ProductAvailabilityMapperSaveRequest paMapperSaveRequest;
    private final ProductAvailabilityMapperSaveResponse paMapperSaveResponse;
    private final ProductAvailabilityRepository productAvailabilityRepository;

    @Override
    public AvailabilitySaveResponse save(AvailabilitySaveRequest availabilitySaveRequest) {
        Availability availability = GeneralSaveOperation
                                        .builder()
                                        .mapper(paMapperSaveRequest)
                                        .repository(productAvailabilityRepository)
                                        .validator(validator)
                                        .build()
                                        .save(availabilitySaveRequest);

        return paMapperSaveResponse.map(availability);
    }

    @Override
    public Page<Availability> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productAvailabilityRepository.findAll(pageRequest);
    }

    @Override
    public void delete(String id) {
        GeneralDeleteOperation
                .builder()
                .repository(productAvailabilityRepository)
                .exceptionSupplier(() -> new NotFoundException("product availability with ID: " + id + " was not found"))
                .build()
                .delete(id);
    }
}
