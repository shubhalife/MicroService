package com.neo.OrderService.exception;


import com.neo.OrderService.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class OrderResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private ErrorResponse errorResponse;

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> productNotFoundHandler(CustomException customException){
        errorResponse.setMessage(customException.getMessage());
        errorResponse.setErrorCode(customException.getErrorCode());


        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(customException.getStatus()));
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse genericException(Exception exception){
        errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        errorResponse.setMessage(exception.getMessage());
        return errorResponse;
    }


}
