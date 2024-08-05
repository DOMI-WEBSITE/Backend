package com.c1848tjavaangular.domi.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerNotFoundException(AppNotFoundException ex){
        ErrorDto errorDto = new ErrorDto();

        errorDto.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimestamp(new Date());

        return new ResponseEntity<ErrorDto>(errorDto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AppConflictException.class)
    public ResponseEntity<ErrorDto> handlerConflictException(AppConflictException ex){
        ErrorDto errorDto = new ErrorDto();

        errorDto.setStatusCode(HttpStatus.CONFLICT.value());
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimestamp(new Date());

        return new ResponseEntity<ErrorDto>(errorDto,HttpStatus.CONFLICT);
    }
    @ExceptionHandler(JwtAuthenticationException.class)
    public ResponseEntity<ErrorDto> handlerAuthenticationCredentialsNotFoundException(JwtAuthenticationException ex){

        ErrorDto errorDto = new ErrorDto();

        errorDto.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimestamp(new Date());

        return new ResponseEntity<ErrorDto>(errorDto,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handlerConstrainViolationException(ConstraintViolationException exception){
        String errorMensaje = "Error de validacion: "+exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() + " "+constraintViolation.getMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.badRequest().body(errorMensaje);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handlerInternalServer(Exception ex){

        ErrorDto errorDto = new ErrorDto();

        errorDto.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setMessage(ex.getMessage());
        errorDto.setTimestamp(new Date());

        return new ResponseEntity<ErrorDto>(errorDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
