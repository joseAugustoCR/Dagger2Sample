package com.example.daggersample.utils;

import com.example.daggersample.networking.WrapperResponse;
import com.example.daggersample.ui.main.Resource;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class WrapperResponseBodyConverter<T>
        implements Converter<ResponseBody, T> {
    private Converter<ResponseBody, WrapperResponse<T>> converter;

    public WrapperResponseBodyConverter(Converter<ResponseBody,
            WrapperResponse<T>> converter) {
        this.converter = converter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        WrapperResponse<T> response = converter.convert(value);

//        if(response.)
            return response.getData();

        // RxJava will call onError with this exception
//        throw new RuntimeException();
    }
}