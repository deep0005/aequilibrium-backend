package com.aequilibrium.transformers.dto;


public class GeneralResponse {

    private String message;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static GeneralResponse getResponse(Boolean type, Object body){
        GeneralResponse newResponse = new GeneralResponse();
        newResponse.message = type ? "success" : "false";

        newResponse.data = body;

        return newResponse;
    }
}
