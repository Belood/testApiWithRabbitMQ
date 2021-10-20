package com.belood.MQListener.infrastructure.messaging;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.domain.client.service.ClientService;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class RabbitMQListener {

  @Autowired
  @Qualifier("rabbitMQListenerLogger")
  private Logger logger;

  @Autowired
  private ClientService clientService;


  @RabbitListener(queues = "${messaging.rabbitmq.queue}")
  public void receiveClient(Client client) {
      logger.info("Received message from RabbitMQ:" + client.toString());
      clientService.save(client);
  }

}
