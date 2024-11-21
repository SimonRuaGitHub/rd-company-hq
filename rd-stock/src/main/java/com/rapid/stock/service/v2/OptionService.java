package com.rapid.stock.service.v2;

import com.rapid.stock.model.v2.Option;
import com.rapid.stock.model.v2.OptionCategory;
import com.rapid.stock.model.v2.ProductVersion;

import java.util.List;

public interface OptionService {
     List<Option> save(ProductVersion productVersion, List<OptionCategory> optionCategoryList);
     List<Option> save(OptionCategory optionCategory, List<ProductVersion> productVersions);
}
