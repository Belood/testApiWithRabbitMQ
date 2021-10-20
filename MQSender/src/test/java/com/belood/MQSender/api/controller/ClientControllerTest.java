package com.belood.MQSender.api.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;

import com.belood.MQSender.domain.client.model.Client;
import com.belood.MQSender.domain.client.service.ClientService;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private Client createRandomClient(){
      Client client = new Client();
      client.setNom(randomAlphabetic(5));
      client.setPrenom(randomAlphabetic(5));
      client.setAddresse(randomAlphabetic(5));
      int hundredYears = 100 * 365;
      client.setDateDeNaissance(LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears)));
      return client;
    }

    @Test
    public void givenAClient_WhenPostClient_thenReturnHttpAccepted() throws Exception {
      Client client = createRandomClient();
      doNothing().when(clientService).create(client);

      String asserted = clientController.postNewClient(client);

      assertEquals("Message successfully sent to the RabbitMQ",asserted);
    }
}
