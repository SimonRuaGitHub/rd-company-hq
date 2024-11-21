package com.rapid.stock.service.v2;

import com.rapid.stock.model.v2.Option;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ProductVersion;
import com.rapid.stock.repository.v2.OptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OptionServiceImp implements OptionService {

    private final OptionRepository optionRepository;

    @Override
    public List<Option> save(ProductVersion productVersion, List<OptionCategory> optionCategories) {
        Set<Option> options = optionCategories.stream().map(optionCategory -> {
            Option option = new Option();
            option.setProductVersion(productVersion);
            option.setOptionCategory(optionCategory);

            return option;
        }).collect(Collectors.toSet());

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
