package com.rapid.stock.mapper.v2;

public interface Mapper {
    public <T,G> T mapToEntity(G dto);
}
