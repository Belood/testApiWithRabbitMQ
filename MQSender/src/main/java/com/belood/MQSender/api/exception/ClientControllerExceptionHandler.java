package com.belood.MQSender.api.exception;

import org.slf4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientControllerExceptionHandler {

    @Autowired
    @Qualifier("clientControllerExceptionHandlerLogger")
    private Logger logger;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ApiError> handleException(Exception e){
      logger.error(e.getMessage(),e);
      ApiError apiError = new ApiError("INTERNAL_SERVER_ERROR","Internal Server Error");
      return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({AmqpException.class})
    public ResponseEntity<ApiError> handleAmqpException(AmqpException e){
      logger.error(e.getMessage(),e);
      ApiError apiError = new ApiError("AMQP_ERROR","Error sending message to the RabbitMQ");
      return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}