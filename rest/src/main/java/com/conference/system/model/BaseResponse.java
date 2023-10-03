package com.conference.system.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public abstract class BaseResponse<T> {
    private HttpStatus status;
    private T body;
    private String message;
}
