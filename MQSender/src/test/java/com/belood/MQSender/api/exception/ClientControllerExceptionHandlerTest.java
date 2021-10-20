package com.belood.MQSender.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ClientControllerExceptionHandlerTest {

  @Mock
  private Logger logger;

  @InjectMocks
  private ClientControllerExceptionHandler clientControllerExceptionHandler;


  @Test
  public void givenAnException_whenHandleException_thenLogTheExceptionAndReturnAnInternalServerError(){
    Exception exception = new Exception("anErrorMessage");

    ResponseEntity<ApiError> asserted = clientControllerExceptionHandler.handleException(exception);

    verify(logger).error(exception.getMessage(),exception);
    assertEquals("INTERNAL_SERVER_ERROR",asserted.getBody().getCode());
    assertEquals("Internal Server Error",asserted.getBody().getMessage());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,asserted.getStatusCode());
  }

  @Test
  public void givenAnAmqpException_whenHandleAmqpException_thenLogTheExceptionAndReturnAnInternalServerError(){
    AmqpException exception = new AmqpException("anAmqpException");

    ResponseEntity<ApiError> asserted = clientControllerExceptionHandler.handleAmqpException(exception);

    verify(logger).error(exception.getMessage(),exception);
    assertEquals("AMQP_ERROR",asserted.getBody().getCode());
    assertEquals("Error sending message to the RabbitMQ",asserted.getBody().getMessage());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,asserted.getStatusCode());
  }
}
