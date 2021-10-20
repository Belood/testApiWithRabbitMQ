package com.belood.MQListener.infrastructure.repository;

import com.belood.MQListener.infrastructure.client.repository.dto.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientDto,Integer> {
}
