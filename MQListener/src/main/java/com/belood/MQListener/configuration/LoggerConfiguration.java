package com.belood.MQListener.configuration;

import com.belood.MQListener.api.exception.ClientControllerExceptionHandler;
import com.belood.MQListener.infrastructure.messaging.RabbitMQListener;
import com.belood.MQListener.service.client.ClientServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfiguration {

  @Bean
  Logger rabbitMQListenerLogger(){
    return LoggerFactory.getLogger(RabbitMQListener.class);
  }

  @Bean
  Logger clientServiceImplLogger(){
    return LoggerFactory.getLogger(ClientServiceImpl.class);
  }

  @Bean
  public Logger clientControllerExceptionHandlerLogger(){
    return LoggerFactory.getLogger(ClientControllerExceptionHandler.class);
  }
}
