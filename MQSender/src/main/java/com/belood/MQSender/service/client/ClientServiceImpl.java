package com.belood.MQSender.service.client;

import com.belood.MQSender.domain.client.model.Client;
import com.belood.MQSender.domain.client.service.ClientService;
import com.belood.MQSender.domain.messaging.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
  @Autowired
  private MQSender mqSender;

  public void create(Client client){
      mqSender.send(client);
  }
}
