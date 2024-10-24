package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.OptionCategory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OptionCategoryRepository extends JpaRepository<OptionCategory, Long> {
    boolean existsByNameAndCompanyId(String name, String companyId);
    boolean existsByLabelAndCompanyId(String label, String companyId);
}
