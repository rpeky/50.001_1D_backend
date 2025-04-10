package com.travelapp.api.globalnonsense.responserequestwrappers;

public class ApiRequest<T> {
    private T data;

    public ApiRequest() {
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
