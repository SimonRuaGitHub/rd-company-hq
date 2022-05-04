package com.rapid.stock.service;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.RackMapperSaveRequest;
import com.rapid.stock.model.Rack;
import com.rapid.stock.repository.RackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
@AllArgsConstructor
public class RackServiceImp implements RackService{

    private final RackRepository rackRepository;
    private final RackMapperSaveRequest rackMapper;
    private final Validator validator;

    @Override
    public Rack save(RackDto rackDto) {

           Rack rack = rackMapper.mapRackSaveRequest(rackDto);

           Set<ConstraintViolation<Object>> violations = validator.validate(rack);

           if(!violations.isEmpty()){
              throw new InvalidDataFieldException("Some of the fields have invalid have invalid data or no data at all", violations);
           }

           try {
                  return rackRepository.save(rack);
           } catch (Exception ex){
                  ex.printStackTrace();
                  throw new SaveException("Failed to save following rack with name: "+rack.getName());
           }
    }
}
