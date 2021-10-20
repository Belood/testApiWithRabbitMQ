package com.belood.MQListener.infrastructure.client.repository.dto;


import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name= "CLIENT")
public class ClientDto {
 @Id
 @Column(name = "ID")
 @GeneratedValue
 private Integer id;
 @Column(name = "NOM")
 private String nom;
 @Column(name = "PRENOM")
 private String prenom;
 @Column(name = "DATE_DE_NAISSANCE")
 private Date dateDeNaissance;
 @Column(name = "ADDRESSE")
 private String addresse;

}
