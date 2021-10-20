package com.belood.MQListener.infrastructure.client.repository.mapping;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.infrastructure.client.repository.dto.ClientDto;
import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;

public class ClientMapperTest {

  private ClientMapper clientMapper = new ClientMapper();

  private ClientDto createRandomClientDto() {
    ClientDto clientDto = new ClientDto();
    clientDto.setNom(randomAlphabetic(5));
    clientDto.setPrenom(randomAlphabetic(5));
    clientDto.setAddresse(randomAlphabetic(5));
    int hundredYears = 100 * 365;
    clientDto.setDateDeNaissance(
        Date.valueOf(
            LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears))));
    return clientDto;

  }

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
  public void givenAClientDto_whenToClient_thenReturnAClient(){
    ClientDto clientDto = createRandomClientDto();

    Client asserted = clientMapper.toClient(clientDto);

    assertEquals(clientDto.getNom(),asserted.getNom());
    assertEquals(clientDto.getPrenom(),asserted.getPrenom());
    assertEquals(clientDto.getAddresse(),asserted.getAddresse());
    assertEquals(clientDto.getDateDeNaissance().toLocalDate(),asserted.getDateDeNaissance());

  }

  @Test
  public void givenAClient_whenToClientDto_thenReturnAClientDto(){
    Client client = createRandomClient();

    ClientDto asserted = clientMapper.toClientDto(client);

    assertEquals(client.getNom(),asserted.getNom());
    assertEquals(client.getPrenom(),asserted.getPrenom());
    assertEquals(client.getAddresse(),asserted.getAddresse());
    assertEquals(client.getDateDeNaissance(),asserted.getDateDeNaissance().toLocalDate());
  }
}
