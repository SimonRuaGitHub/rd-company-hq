package com.rapid.stock.service.v2;

import com.rapid.stock.model.v2.Option;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.OptionType;
import com.rapid.stock.model.v2.ProductVersion;

import java.util.List;
import java.util.Map;

public interface OptionService {
     List<Option> save(ProductVersion productVersion, Map<OptionCategory, OptionType> optionCategoryByType);
     List<Option> save(OptionCategory optionCategory, List<ProductVersion> productVersions);
}
