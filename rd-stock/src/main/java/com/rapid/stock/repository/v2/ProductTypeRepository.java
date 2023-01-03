package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
}
