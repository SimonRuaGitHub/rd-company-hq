package com.rapid.stock.service.v2;

import com.rapid.stock.dto.AvailabilityDTO;
import com.rapid.stock.exception.InvalidDataFieldException;
import com.rapid.stock.exception.SaveException;
import com.rapid.stock.mapper.v2.ProductAvailabilityMapper;
import com.rapid.stock.model.v2.Availability;
import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.repository.v2.ProductAvailabilityRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
@Profile("rational-db")
public class ProductAvailabilityServiceImp implements ProductAvailabilityService{

    private final Validator validator;
    private final ProductAvailabilityMapper productAvailabilityMapper;
    private final ProductAvailabilityRepository productAvailabilityRepository;

    @Override
    public Availability save(AvailabilityDTO availabilityDTO) {
        final Availability productAvailability = productAvailabilityMapper.mapToAvailability(availabilityDTO);

        Set<ConstraintViolation<Object>> violations = validator.validate(productAvailability);

        if(!violations.isEmpty()) {
            throw new InvalidDataFieldException("Some of the fields have invalid data or no data at all", violations);
        }

       Availability savedProdAvailability = null;

        try {
            savedProdAvailability = productAvailabilityRepository.save(productAvailability);
        } catch(Exception ex){
            ex.printStackTrace();
            throw new SaveException("Failed to create following product availability with id: "+ productAvailability.getId());
        }

        return savedProdAvailability;
    }
}
