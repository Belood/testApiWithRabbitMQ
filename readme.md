# Projet de test d'API avec RabbitMQ

Ce projet est composé de deux microservices un **sender** et un **listener** 
Il consiste en une API permettant de sauvegarder un client envoyé via une requete REST au **sender** qui
envoie ensuite un message a une queue RabbitMQ. Le **listener** recupère ensuite ce message et s'occupe
de sauvegarder le client dans une base de donnée. Il expose egalement un endpoint permettant de recuperer
l'ensemble des clients sauvegardés. 

Le format de données utilisé est le **JSON**.

---
## Prérequis
Java 17+

Maven 3+ *pour build*

RabbitMQ 3+

Docker *uniquement si vous utiliser la version conteneurisée du projet*

---

## mq-sender
Le sender expose un endpoint REST permettant d'envoyer un client. 


| Endpoint | Method | Request | Content-Type | Response |
| ---------| ------ | ---- | ------------ | --- |
| v1/client | POST  | <pre lang="json">{<br>  "nom": "string",<br>  "nom": "prenom",<br>  "addresse": "string",<br>  "dateDeNaissance": "yyyy-MM-dd" <br>}</pre>| application/json | "String" |



---
## mq-listener

Le listener expose un endpoint REST permettant de recuperer tous les clients.

Il sauvegarde les clients reçus dans une base de donneé en mémoire **H2** pour des raisons de simplicité.


| Endpoint | Method | Request | Content-Type | Response |
| ---------| ------ | ---- | ------------ | --- |
| v1/client | GET  | | application/json| <pre lang="json">[{<br>  "nom": "string",<br>  "nom": "prenom",<br>  "addresse": "string",<br>  "dateDeNaissance": "yyyy-MM-dd" <br>}]</pre> |


---

## Installation, configuration
On suppose l'existance et la configuration préalable de RabbitMQ.

Par defaut le mq-sender est sur le port **8081** et le mq-listener sur le port **8080**
### Executable Java
Aucune installation est nécessaire. Les deux sont éxécutables dans le dossier *bin*.



Pour modifier la configuration aller a l'interieur du jar et editer le fichier de configuration suivant:

`BOOT-INF/classes/application.properties`

En particulier pour configurer RabbitMQ. *(Les valeurs par default sont affichés)*
````
spring.rabbitmq.host=172.27.112.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=test
spring.rabbitmq.password=test
````

Pour utiliser le projet. Executer les deux jar present dans *bin*.
Les deux devraient être exposés aux URL suivants (*par default*)

mq-sender: http://localhost:8081/

mq-listener: http://localhost:8080/

### Docker
Dans une invite de commande aller a la racine du projet *mq-sender* ou *mq-listener* et entrer la commande suivante.

`mvn package dockerfile:build` 

Cela devrait créer une image docker dans votre repository local docker sous le nom **test/mq-sender** ou **test/mq-listener**.

Pour modifier la configuration de RabbitMQ ou du port dans le projet avant de lancer un build aller dans les sources du projet et editer le fichier suivant en fonction de votre configuration
*(Les valeurs par default sont affichés)*

`src/main/resources/application.properties`
````
server.port=8081
spring.rabbitmq.host=172.27.112.1
spring.rabbitmq.port=5672
spring.rabbitmq.username=test
spring.rabbitmq.password=test
````

Lancer ensuite les conteneurs avec la commande:

mq-sender:`docker run -it -p 8081:8081 test/mq-sender`

mq-listener:`docker run -it -p 8080:8080 test/mq-listener`

Les deux devraient être exposés aux URL suivants (*par default*)

mq-sender: http://localhost:8081/

mq-listener: http://localhost:8080/

**Remarque: La configuration de docker, le linking ou autre avec RabbitMQ n'est pas l'objet du projet et est laissée a l'utilisateur en fonction de sa configuration**

---

## Remarques
Un systeme de log et de gestion d'erreurs est présent mais pour des raison de simplicité celui-ci est très basique
et general. De même un système de securité est présent mais authorise toutes les requêtes et ne necessite pas de sécurité par simplicité