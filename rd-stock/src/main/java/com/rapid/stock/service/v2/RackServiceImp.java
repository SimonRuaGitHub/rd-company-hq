package com.rapid.stock.service.v2;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.dto.RackSaveResponse;
import com.rapid.stock.exception.NotFoundException;
import com.rapid.stock.mapper.v2.request.RackMapperSaveRequest;
import com.rapid.stock.mapper.v2.response.RackMapperSaveResponse;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.RackRepository;
import com.rapid.stock.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class RackServiceImp implements RackService {

    private final RackRepository rackRepository;
    private final RackMapperSaveRequest rackMapperSaveRequest;
    private final RackMapperSaveResponse rackMapperSaveResponse;
    private final Validator validator;

    @Override
    public RackSaveResponse save(RackSaveRequest rackSaveRequest) {
        Rack rack = GeneralSaveOperationService
                    .builder()
                    .validator(validator)
                    .mapper(rackMapperSaveRequest)
                    .repository(rackRepository)
                    .build()
                    .save(rackSaveRequest);

        RackSaveResponse rackSaveResponse = rackMapperSaveResponse.map(rack);

        return rackSaveResponse;
    }

    @Override
    public Page<Rack> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return rackRepository.findAll(pageRequest);
    }

    @Override
    public void delete(Long rackId) {
        Supplier<RuntimeException> exceptionSupplier =
                () -> new NotFoundException("Rack ID: " + rackId + " was not found");

        GeneralDeleteOperationService deleteOperationService = GeneralDeleteOperationService
                .builder()
                .repository(rackRepository)
                .exceptionSupplier(exceptionSupplier)
                .build();

        deleteOperationService.delete(rackId);
    }
}
