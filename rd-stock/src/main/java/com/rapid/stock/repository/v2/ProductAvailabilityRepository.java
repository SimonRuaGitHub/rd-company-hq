package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductAvailabilityRepository extends JpaRepository<Availability, Long> {
}
