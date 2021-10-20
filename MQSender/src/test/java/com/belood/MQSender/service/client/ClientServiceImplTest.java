package com.belood.MQSender.service.client;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Mockito.verify;

import com.belood.MQSender.domain.client.model.Client;
import com.belood.MQSender.service.client.ClientServiceImpl;
import com.belood.MQSender.domain.messaging.MQSender;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

  @Mock
  private MQSender mqSender;

  @InjectMocks
  private ClientServiceImpl clientService;

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
  public void givenAClient_whenCreated_thenCallSend(){
    Client client = createRandomClient();
    clientService.create(client);

    verify(mqSender).send(client);
  }
}
