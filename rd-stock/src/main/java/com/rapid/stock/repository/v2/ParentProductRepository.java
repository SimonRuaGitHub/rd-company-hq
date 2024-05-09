package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.ParentProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentProductRepository extends JpaRepository<ParentProduct, Long> {
    boolean existsByNameAndCompanyId(String name, String companyId);
}
