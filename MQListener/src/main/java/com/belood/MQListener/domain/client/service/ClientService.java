package com.belood.MQListener.domain.client.service;

import com.belood.MQListener.domain.client.model.Client;
import java.util.List;

public interface ClientService {

  List<Client> getClients();
  void save(Client client);

}
