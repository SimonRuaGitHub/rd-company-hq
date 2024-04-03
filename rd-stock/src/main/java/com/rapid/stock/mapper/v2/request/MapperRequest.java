package com.rapid.stock.mapper.v2.request;

public interface MapperRequest<T,G>{
    public T mapToEntity(G dto);
}
