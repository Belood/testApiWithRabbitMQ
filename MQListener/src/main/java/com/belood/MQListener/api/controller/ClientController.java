package com.belood.MQListener.api.controller;


import com.belood.MQListener.domain.client.model.Client;
import com.belood.MQListener.domain.client.service.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ClientController {

  @Autowired
  private ClientService clientService;

  @GetMapping(value="/client", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Client> getClients(){
    return clientService.getClients();
  }




}
