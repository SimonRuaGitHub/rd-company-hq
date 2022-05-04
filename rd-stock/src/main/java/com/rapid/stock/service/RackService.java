package com.rapid.stock.service;

import com.rapid.stock.dto.RackDto;
import com.rapid.stock.model.Rack;

public interface RackService {
    public Rack save(RackDto rackDto);
}
