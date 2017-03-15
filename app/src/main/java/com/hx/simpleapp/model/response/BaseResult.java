package com.hx.simpleapp.model.response;


public interface BaseResult<T> {
    boolean isOk();

    T getData();
}
