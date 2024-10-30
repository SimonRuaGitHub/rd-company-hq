package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.Addition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionRepository extends JpaRepository<Addition, Long> {
    boolean existsByNameAndCompanyId(String name, String companyId);
}