package com.equitybank.billing.parser.domain.http;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Response<T> implements Serializable {
    @SerializedName("data")
    private T data;
    @SerializedName("message")
    private String message;
    @SerializedName("status")
    private Status status;

    public Response(){

    }
    public Response(T data, String message, Status status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public enum Status {
        SUCCESS, ERROR, FAILED
    }
}
