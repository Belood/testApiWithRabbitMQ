package com.belood.MQSender.api.controller;

import com.belood.MQSender.domain.client.model.Client;
import com.belood.MQSender.domain.client.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService){
    this.clientService = clientService;
  }

  @PostMapping(value="/client", consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public String postNewClient(@RequestBody Client client){

    clientService.create(client);
    return "Message successfully sent to the RabbitMQ";

  }




}
