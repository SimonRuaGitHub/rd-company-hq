package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.ParentProduct;
import com.rapid.stock.model.v2.ProductVersion;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductVersionRepository extends JpaRepository<ProductVersion, Long> {
    List<ProductVersion> findByParentProduct(ParentProduct parentProduct);
}
