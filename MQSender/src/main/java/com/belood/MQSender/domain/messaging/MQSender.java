package com.belood.MQSender.domain.messaging;

import com.belood.MQSender.domain.client.model.Client;

public interface MQSender {
  void send(Client client);
}
