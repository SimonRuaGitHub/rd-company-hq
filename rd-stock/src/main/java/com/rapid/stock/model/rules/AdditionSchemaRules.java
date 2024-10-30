package com.rapid.stock.model.rules;

import com.rapid.stock.exception.NotValidAdditionException;
import com.rapid.stock.repository.v2.AdditionRepository;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Addition;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class AdditionSchemaRules {

    private final AdditionRepository additionRepository;
    private final GeneralSchemaRules generalSchemaRules;

    public void validateNameAlreadyExistsForCompany(String additionName, String companyId) {
        Predicate<String> companyOwnsAdditionName = name -> additionRepository.existsByNameAndCompanyId(
                name,
                companyId
        );

        generalSchemaRules.validateNameAlreadyExistsForCompany(
                companyOwnsAdditionName,
                additionName,
                () -> new NotValidAdditionException(
                        String.format(
                                "There is already an Addition with name: %s for companyId: %s",
                                additionName,
                                companyId
                        )
                )
        );
    }


}
