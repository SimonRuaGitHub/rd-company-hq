package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.Rack;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Profile("rational-db")
public interface RackRepository extends JpaRepository<Rack, Long> {
    boolean existsByNameAndCompanyId(String name, String companyId);
}
