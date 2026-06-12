package com.uteam.person_movie.Presentation.Advisors;

import com.uteam.person_movie.Application.Contracts.Results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result<Void> handle(Exception ex) {

        ex.printStackTrace();

        return Result.failure(
                HttpStatus.INTERNAL_SERVER_ERROR,
                List.of(ex.getMessage())
        );
    }
}
