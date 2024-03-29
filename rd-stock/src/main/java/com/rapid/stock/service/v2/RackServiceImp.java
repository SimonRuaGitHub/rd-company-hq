package com.rapid.stock.service.v2;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.RackMapperSaveRequest;
import com.rapid.stock.model.v2.Rack;
import com.rapid.stock.repository.v2.RackRepository;
import com.rapid.stock.service.RackService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class RackServiceImp implements RackService {

    private final RackRepository rackRepository;
    private final RackMapperSaveRequest rackMapper;
    private final Validator validator;

    @Override
    public Rack save(RackDto rackDto) {
        return GeneralSaveOperationService
                .builder()
                .validator(validator)
                .mapper(rackMapper)
                .repository(rackRepository)
                .build()
                .save(rackDto);
    }

}
