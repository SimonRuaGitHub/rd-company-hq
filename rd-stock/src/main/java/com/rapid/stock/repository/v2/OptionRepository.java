package com.rapid.stock.repository.v2;

import com.rapid.stock.model.v2.Option;
import com.rapid.stock.model.v2.OptionKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, OptionKey> {
}
