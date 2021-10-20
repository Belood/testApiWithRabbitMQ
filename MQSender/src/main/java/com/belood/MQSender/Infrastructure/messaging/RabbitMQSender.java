package com.belood.MQSender.Infrastructure.messaging;

import com.belood.MQSender.domain.client.model.Client;
import com.belood.MQSender.domain.messaging.MQSender;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender implements MQSender {

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("${messaging.rabbitmq.exchange}")
  private String exchange;

  @Value("${messaging.rabbitmq.routingkey}")
  private String routingKey;

  @Autowired
  @Qualifier("rabbitMQSenderLogger")
  private Logger logger;

  @Override
  public void send(Client client) {
      rabbitTemplate.convertAndSend(exchange,routingKey,client);
      logger.info("Message "+ client.toString() + " successfully sent to the RabbitMQ");
  }
}
