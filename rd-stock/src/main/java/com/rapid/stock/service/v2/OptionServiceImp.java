package com.rapid.stock.service.v2;

import com.amazonaws.services.apigateway.model.Op;
import com.rapid.stock.model.v2.Option;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.OptionType;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OptionServiceImp implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public List<Option> save(ProductVersion productVersion,  Map<OptionCategory, OptionType> optionCategoryWithType) {

        Set<Option> options = new HashSet<>();

        optionCategoryWithType.forEach(
                (optionCategory, optionType) -> {
                    Option option = new Option();
                    option.setProductVersion(productVersion);
                    option.setOptionCategory(optionCategory);
                    option.setOptionType(optionType);

                    options.add(option);
        });

        return options.stream().map(optionRepository::save).collect(Collectors.toList());
    }

    @Override
    public List<Option> save(OptionCategory optionCategory, List<ProductVersion> productVersions) {
        Set<Option> options = productVersions.stream().map(pv -> {
            Option option = new Option();
            option.setProductVersion(pv);
            option.setOptionCategory(optionCategory);

            return option;
        }).collect(Collectors.toSet());

        return options.stream().map(optionRepository::save).collect(Collectors.toList());
    }
}
