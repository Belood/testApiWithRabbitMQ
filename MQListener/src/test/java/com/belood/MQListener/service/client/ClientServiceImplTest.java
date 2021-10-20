package com.belood.MQListener.service.client;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.infrastructure.client.repository.dto.ClientDto;
import com.belood.MQListener.infrastructure.repository.ClientRepository;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceImplTest {

  @Mock
  private ClientRepository clientRepository;

  @Mock
  private Logger logger;

  @InjectMocks
  @Autowired
  private ClientServiceImpl clientService;


  private ClientDto createRandomClientDto() {
    ClientDto clientDto = new ClientDto();
    clientDto.setNom(randomAlphabetic(5));
    clientDto.setPrenom(randomAlphabetic(5));
    clientDto.setAddresse(randomAlphabetic(5));
    int hundredYears = 100 * 365;
    clientDto.setDateDeNaissance(
        Date.valueOf(LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears))));
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
  public void whenGetClients_thenReturnAListOfClients(){
    ClientDto clientDto = createRandomClientDto();
    List<ClientDto> clientDtos = new ArrayList<>();
    clientDtos.add(clientDto);
    when(clientRepository.findAll()).thenReturn(clientDtos);

    List<Client> asserted = clientService.getClients();

    assertEquals(clientDto.getNom(),asserted.get(0).getNom());
    assertEquals(clientDto.getPrenom(),asserted.get(0).getPrenom());
    assertEquals(clientDto.getAddresse(),asserted.get(0).getAddresse());
    assertEquals(clientDto.getDateDeNaissance().toLocalDate(),asserted.get(0).getDateDeNaissance());

  }

  @Test
  public void givenAClient_whenSave_theCallTheRepository(){
    Client client = createRandomClient();

    clientService.save(client);

    verify(clientRepository).save(any(ClientDto.class));
    verify(logger).info(client.toString() + " saved to database");
  };

  @Test
  public void givenADataAccessException_whenSave_thenLogTheException(){
    Client client = createRandomClient();
    DataAccessException exception = new DuplicateKeyException("aDataException");
    when(clientRepository.save(any(ClientDto.class))).thenThrow(exception);

    clientService.save(client);

    verify(logger).error(exception.getMessage(),exception);
  }


}
