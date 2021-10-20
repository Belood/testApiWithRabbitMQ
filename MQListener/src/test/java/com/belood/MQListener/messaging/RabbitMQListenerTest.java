package com.belood.MQListener.messaging;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Mockito.verify;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.domain.client.service.ClientService;
import com.belood.MQListener.infrastructure.messaging.RabbitMQListener;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

@ExtendWith(MockitoExtension.class)
public class RabbitMQListenerTest {

  @Mock
  private Logger logger;

  @Mock
  private ClientService clientService;

  @InjectMocks
  private RabbitMQListener rabbitMQListener;


  private Client createRandomClient() {
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
  public void givenAClient_whenReceiveClient_thenCallSave(){
    Client client = createRandomClient();

    rabbitMQListener.receiveClient(client);

    verify(logger).info("Received message from RabbitMQ:" + client.toString());
    verify(clientService).save(client);

  }
}
