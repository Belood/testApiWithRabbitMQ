package com.belood.MQListener.api.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.domain.client.service.ClientService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
  public void whenGetClient_thenReturnAListOfClients() throws Exception {
    Client client1 = createRandomClient();
    Client client2 = createRandomClient();
    List<Client> clients = new ArrayList<>();
    clients.add(client1);
    clients.add(client2);
    when(clientService.getClients()).thenReturn(clients);

    List<Client> asserted = clientController.getClients();

    assertEquals(clients,asserted);
  }

}
