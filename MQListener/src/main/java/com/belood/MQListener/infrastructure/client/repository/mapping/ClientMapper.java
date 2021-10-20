package com.belood.MQListener.infrastructure.client.repository.mapping;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.infrastructure.client.repository.dto.ClientDto;
import java.sql.Date;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

  public Client toClient(ClientDto clientDto){
    Client client = new Client();
    client.setNom(clientDto.getNom());
    client.setPrenom(clientDto.getPrenom());
    client.setDateDeNaissance(clientDto.getDateDeNaissance().toLocalDate());
    client.setAddresse(clientDto.getAddresse());
    return client;
  }

  public ClientDto toClientDto(Client client){
    ClientDto clientDto = new ClientDto();
    clientDto.setNom(client.getNom());
    clientDto.setPrenom(client.getPrenom());
    clientDto.setDateDeNaissance(Date.valueOf(client.getDateDeNaissance()));
    clientDto.setAddresse(client.getAddresse());
    return clientDto;
  }


}
