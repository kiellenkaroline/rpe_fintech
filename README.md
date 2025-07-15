# RPE Fintech

Este projeto foi desenvolvido como parte do processo seletivo para a vaga de estágio fullstack.
O objetivo principal é criar um sistema simples e funcional para gerenciar clientes, faturas e
pagamentos, simulando parte do ecossistema real da empresa.

## O sistema consiste em:

- Uma API REST construída com Java 21 e Spring Boot para cadastro, consulta, atualização e gerenciamento de clientes, faturas e pagamentos;
- Persistência de dados com Spring Data JPA e banco de dados MySQL, com migrações controladas via Flyway;
- Documentação da API gerada automaticamente com Springdoc OpenAPI (Swagger UI);
- Um modelo arquitetural que separa responsabilidades entre camadas de controller, serviço, repositório e modelo, facilitando manutenibilidade e escalabilidade;
- Possibilidade futura de integração com interface web simples para interação básica dos usuários.

## Tecnologias utilizadas:

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Flyway
- Springdoc OpenAPI (Swagger)

## Pré-requisitos

  - Java 21 instalado
  - MySQL rodando local e via docker
  - Variáveis de ambiente configuradas para conexão com Banco de Dados

## Estrutura do projeto

  controller/ - APIs REST
  service/ - Regras de negócio
  model/ - Entidades JPA
  repository/ / Acesso ao banco de dados

  ## Como iniciar o projeto

   - docker-compose build 
   - docker-compose up
   - acessar front-end em localhost:3000

   ### testes back-end
   ./gradlew test
    
