# Desafio de Programação

Este projeto é uma API REST desenvolvida em Java com Spring Boot para receber transações e calcular estatísticas sobre elas, conforme descrito no desafio proposto pelo [Desafio do Itau](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior?tab=readme-ov-file).

## Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Web
- Lombok 
- OpenApi
- Actuator
- Maven

## Endpoints da API
###### Todas uitilizam o caminho (http://localhost:8080)
### 1. Criar uma Transação

POST /transacao

Requisição:

```json
{
    "valor": 123.45,
    "dataHora": "2025-02-15T12:34:56.789-03:00"
}
```

Respostas:

201 Created - A transação foi aceita.

422 Unprocessable Entity - Transação inválida (transação futura, valor negativo, etc.).

400 Bad Request - JSON inválido.


### 2. Apagar Todas as Transações

DELETE /transacao

Resposta:

200 OK - Todas as transações foram apagadas.


### 3. Obter todas as transações

GET /transacao

resposta:

````json
[
    {
        "valor": 234.45,
        "dataHora": "2025-02-18T19:33:00Z"
    },
    {
        "valor": 148.65,
        "dataHora": "2025-02-19T21:55:00Z"
    }
]
````

200 OK - Todas as transações foram obtidas

### 4. Obter Estatísticas

- GET /estatistica

Resposta:

```json
{
    "count": 10,
    "sum": 1234.56,
    "avg": 123.456,
    "min": 12.34,
    "max": 123.56
}
```

200 OK - Retorna as estatísticas dos últimos 60 segundos.

- GET /estatistica?lastMinutes=10

Resposta:

```json
{
    "count": 19,
    "sum": 1878.56,
    "avg": 98.84,
    "min": 2.0,
    "max": 532.56
}
```

200 OK - Retorna as estatísticas dos últimos minutos escolhidos.

## Como Executar o Projeto

### Clone o repositório:

```bash
git clone https://github.com/EduardoBmAguiar/desafio_itau.git
```

### Navegue até o diretório do projeto:

````bash
cd desafio_itau
````

### Compile e execute a aplicação:
###### terminal:
````bash
mvnw spring-boot:run
````
###### git bash / power shell:
````bash
./mvnw spring-boot:run
````

## Testes Automatizados

O projeto inclui testes automatizados para garantir o correto funcionamento dos endpoints. Para executá-los, use:
###### terminal:
````bash
mvnw test
````
###### git bash / power shell:
````bash
./mvnw test
````

## Adicionais do projeto

### - Logs

### - melhor tratamento de erros (ExceptionHandler)
````json
{
    "timestamp": "2025-02-20T12:45:08Z",
    "status": 422,
    "error": "TransactionException",
    "message": "negative value",
    "path": "/transacao"
}
````

### - Endpoint de health check
````bash
GET | http://localhost:8080/actuator/health
````

### - tempo de aplicação para calcular as estatísticas
exemplo de resposta: 
````
Method getStatistic executed in 2 ms
````

### - Conteinerização
###### Possui a projeção para ser executado em qualquer ambiente compátivel com o Docker
#### Com o Docker aberto:
Para buildar
````docker
docker build -t desafio-itau:1.0 .
````

Para rodar
```docker
docker run -p 8080:8080 desafio-itau:1.0
```

Para ver se está rodando
```docker
docker ps
```

Para dar stop

```docker
docker stop aed47d9233df->(id obtido no comando: docker ps)
```

### - API documentada no OpenApi
![documentação](https://github.com/EduardoBmAguiar/assets/blob/main/swagger%20desafio-itau.png) 





















