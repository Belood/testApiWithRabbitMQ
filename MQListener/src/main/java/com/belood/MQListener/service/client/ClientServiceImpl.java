package com.belood.MQListener.service.client;

import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.domain.client.service.ClientService;
import com.belood.MQListener.infrastructure.client.repository.mapping.ClientMapper;
import com.belood.MQListener.infrastructure.repository.ClientRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService {

  @Autowired
  private ClientRepository clientRepository;

  @Autowired
  private ClientMapper clientMapper;

  @Autowired
  @Qualifier("clientServiceImplLogger")
  private Logger logger;

  @Override
  public List<Client> getClients() {
    return clientRepository.findAll().stream()
        .map(clientDto -> clientMapper.toClient(clientDto))
        .collect(Collectors.toList());
  }

  @Override
  public void save(Client client){
    try{
      clientRepository.save(clientMapper.toClientDto(client));
      logger.info(client.toString() + " saved to database");
    }catch (DataAccessException e){
      logger.error(e.getMessage(),e);
    }
  }
}
