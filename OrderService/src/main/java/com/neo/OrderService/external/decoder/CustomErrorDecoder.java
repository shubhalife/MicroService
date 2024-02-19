package com.neo.OrderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neo.OrderService.exception.CustomException;
import com.neo.OrderService.model.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;


import java.io.IOException;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        ObjectMapper objectMapper =new ObjectMapper();
        log.info("::{}",response.request().url());
        log.info("::{}",response.request().headers());

        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
            return new CustomException(errorResponse.getMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server Error","INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }

        
    }
}
