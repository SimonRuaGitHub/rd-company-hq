package com.rapid.stock.service;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.model.v2.Rack;
import org.springframework.data.domain.Page;

public interface RackService {
    public Rack save(RackDto rackDto);

    public Page<Rack> getAll(int page, int size);
}
