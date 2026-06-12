package com.uteam.person_movie.Application.Contracts.Results;

import org.springframework.http.HttpStatus;

import java.util.List;

public final class Result<TResponse> {
    public boolean isSuccess;
    public HttpStatus httpStatusCode;
    public String description;
    public List<String> errors;
    public TResponse payload;

    private Result() {};

    public Result<TResponse> withDescription(String description) {
        this.description = description;
        return this;
    }

    public static <TResponse> Result<TResponse> success(HttpStatus status, TResponse payload) {
        Result<TResponse> result = new Result<>();
        result.isSuccess = true;
        result.httpStatusCode = status;
        result.payload = payload;

        return result;
    }

    public static <TResponse> Result<TResponse> failure(HttpStatus status, List<String> errors) {
        Result<TResponse> result = new Result<>();
        result.isSuccess = false;
        result.httpStatusCode = status;
        result.errors = errors;

        return result;
    }
}
