package com.travelapp.api.globalnonsense.responserequestwrappers;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private String errorMessage;
    private Integer errorCode;

    public ApiResponse() {
    }
    public ApiResponse(T data){
        this.data = data;
    }
    public ApiResponse(String errorMessage){
        this.errorMessage = errorMessage;
    }
    public ApiResponse(String errorMessage, Integer errorCode){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
    public ApiResponse(T data, String errorMessage, Integer errorCode) {
        this.data = data;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
