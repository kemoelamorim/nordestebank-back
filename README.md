# nordestebank-back
- Passo a passo para rodar o projeto em DEV
- Requisitos 
  - Java 11
  - Maven
  - SQLServer 19
  - Doker e docker-compose (Opcional) 

1 - Clone o projeto em sua maquina
2 - Suba o banco de dados SQLServer na porta padrão -> 127.0.0.1:1433 (imagem de SQLServer em src/main/docker > docker-compose.yml)
3 - Rode o Script de criação que está na raiz do projeto em scripts > CREATE.sql
4 - Rode o projetos com o comando (./mvnw spring-boot:run)
5 - Rode o Script de INSERT que esta na raiz do projeto em scripts > INSERTS.sql
