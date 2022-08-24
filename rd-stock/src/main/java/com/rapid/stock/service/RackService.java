package com.rapid.stock.service;

import com.rapid.stock.dto.RackDto;

public interface RackService<T> {
    public T save(RackDto rackDto);
}
