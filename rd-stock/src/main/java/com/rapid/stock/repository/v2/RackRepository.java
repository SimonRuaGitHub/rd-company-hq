package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.Rack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RackRepository extends JpaRepository<Rack, Long> {
    boolean existsByNameAndCompanyId(String name, String companyId);
}
