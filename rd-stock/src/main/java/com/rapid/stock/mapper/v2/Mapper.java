package com.rapid.stock.mapper.v2;

public interface Mapper<T,G>{
    public T mapToEntity(G dto);
}
