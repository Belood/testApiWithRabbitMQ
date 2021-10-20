package com.belood.MQSender.infrastructure.messaging;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Mockito.verify;

import com.belood.MQSender.Infrastructure.messaging.RabbitMQSender;
import com.belood.MQSender.domain.client.model.Client;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;

@ExtendWith(MockitoExtension.class)
public class RabbitMQSenderTest {

  @Mock
  private AmqpTemplate rabbitTemplate;

  @Mock
  private Logger logger;

  @Value("${messaging.rabbitmq.exchange}")
  private String exchange;

  @Value("${messaging.rabbitmq.routingkey}")
  private String routingKey;

  @InjectMocks
  private RabbitMQSender rabbitMQSender;

  private Client createRandomClient(){
    Client client = new Client();
    client.setNom(randomAlphabetic(5));
    client.setPrenom(randomAlphabetic(5));
    client.setAddresse(randomAlphabetic(5));
    int hundredYears = 100 * 365;
    client.setDateDeNaissance(
        LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears)));
    return client;
  }


  @Test
  public void givenAClient_whenSend_thenCallConvertAndSendAndLogInfo(){
    Client client = createRandomClient();

    rabbitMQSender.send(client);

    verify(rabbitTemplate).convertAndSend(exchange,routingKey,client);
    verify(logger).info("Message "+ client.toString() + " successfully sent to the RabbitMQ");
  }
}
