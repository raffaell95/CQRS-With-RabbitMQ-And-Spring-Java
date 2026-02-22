# API – Arquitetura CQRS Orientada a Eventos

Projeto backend que simula uma **API de gerenciamento de salão de beleza**, desenvolvido com foco em **arquitetura escalável, desacoplamento de responsabilidades e comunicação assíncrona**.

A aplicação implementa o padrão **CQRS (Command Query Responsibility Segregation)** aliado a mensageria com RabbitMQ, simulando um cenário real de microsserviços orientados a eventos.

---

## Objetivo do Projeto

Demonstrar na prática:

- Separação entre responsabilidades de escrita e leitura (CQRS)
- Arquitetura orientada a eventos
- Comunicação assíncrona entre serviços
- Persistência NoSQL otimizada para cenários distintos
- Containerização de ambiente completo

Este projeto foi desenvolvido com mentalidade de produção, priorizando desacoplamento, escalabilidade e clareza arquitetural.

---

## Visão Arquitetural

A solução é dividida em dois serviços principais:

### Command Service
Responsável por:
- Receber requisições de escrita
- Validar regras de negócio
- Persistir dados
- Publicar eventos no RabbitMQ

### Query Service
Responsável por:
- Consumir eventos
- Atualizar a base de leitura
- Disponibilizar consultas otimizadas

### Comunicação

A comunicação entre os serviços ocorre via **RabbitMQ**, garantindo:

- Baixo acoplamento
- Processamento assíncrono
- Escalabilidade horizontal
- Resiliência

---

## Tecnologias e Stack

### Backend
- Java 17
- Spring Boot
- Spring Web
- Spring Data MongoDB
- Spring AMQP
- Maven

### Infraestrutura
- MongoDB
- RabbitMQ
- Docker
- Docker Compose

---

