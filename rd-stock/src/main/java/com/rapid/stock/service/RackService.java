package com.rapid.stock.service;

import com.rapid.stock.dto.RackSaveRequest;
import com.rapid.stock.dto.RackSaveResponse;
import com.rapid.stock.model.v2.Rack;
import org.springframework.data.domain.Page;

public interface RackService {
    public RackSaveResponse save(RackSaveRequest rackSaveRequest);

    public Page<Rack> getAll(int page, int size);

    public void delete(Long rackId);
}
