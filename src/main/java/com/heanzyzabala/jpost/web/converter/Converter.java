package com.heanzyzabala.jpost.web.converter;

public interface Converter<F, T>{
    T convert(F f);
}
