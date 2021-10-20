package com.belood.MQSender.configuration;

import com.belood.MQSender.Infrastructure.messaging.RabbitMQSender;
import com.belood.MQSender.api.exception.ClientControllerExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfiguration {

  @Bean
  public Logger clientControllerExceptionHandlerLogger(){
    return LoggerFactory.getLogger(ClientControllerExceptionHandler.class);
  }

  @Bean Logger rabbitMQSenderLogger(){
    return LoggerFactory.getLogger(RabbitMQSender.class);
  }


}
