# Projeto Integrador III - O desafio final

Esse projeto integrador faz parte do desenvolvimento dos alunos que participaram do Bootcamp Meli Wave 06.

Trata-se de uma aplicação desenvolvida de forma a implementar uma API REST.
O projeto foi estruturado em cinco requisitos em grupo e um individual.

Essa apresentação é referente ao requisito individual.

## Autores

- [@Sandi Ourique](https://www.github.com/octokatherine)

# Tecnologia Utilizada

- Java 11
- Intelij IDE
- Spring Boot
- Maven
- JUnit

## Documentação

[Documentação](https://link-da-documentação)

# ERR: 
![diagrama](https://myoctocat.com/assets/images/DiagramaERR_Sandi.jpeg)

User Story:

Coleção de casos de teste do Postman: (Sandi-endpoints-Seller.postman_collection.json)



## Documentação da API

#### Retorna todos os vendedores

```http
  GET /api/v6/seller
```

#### Cadastra um vendedor

```http
  POST /api/v6/seller
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Nao é obrigatório**. O ID do pode ser gerado pela api |
| `name`    | `String` |**Obrigatório**. O nome deve ser informado |

#### Atualiza um vendedor

```http
  PUT /api/v6/seller
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |
| `name`    | `String` |**Obrigatório**. O nome deve ser informado |

#### Delete um vendedor
```http
  DELETE /api/v6/seller/{id}
```
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |


#### Retorna todos os produtos que o vendedor possui cadastro


```http
  GET /api/v2/adsenses/list/{seller_id}
```
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |


#### Retorna o id dos produtos e a quantidade que possui em estoque

```http
  GET /api/v2/seller/quantity/{seller_id}
```
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |


#### Retorna os produtos com as vendas finalizadas

```http
  GET /api/v2/seller/purchaseOrder/finished/{seller_id}
```
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |


#### Retorna os produtos com as datas de validade e a contagem regressiva de dias para vencer

```http
  GET /api/v2/seller/dueDateProducts/{seller_id}
```
| `id`      | `Long` | **Nao é obrigatório**. O ID deve ser informado |


# Requisitos em Grupo

## Desafio agregador III

Esse projeto foi desenvolvido por:

- [Andrea](https://github.com/andherreraML)

- [Bel](https://github.com/BelAlbuquerque)

- [Karina](https://github.com/KarinaLimaMeli)

- [Letícia](https://github.com/lecastroMELI)

- [Nana](https://github.com/InajaraPereira)

- [Sandi](https://github.com/sandiouriquemeli)

### Sobre:

Nesse projeto, aplicamos os conteudos que aprendemos até este momento no BootCamp de Java.
Trata-se de uma aplicação desenvolvida de forma a implementar uma API REST.

**Para isso, implementamos os seguntes recursos:**

- `Funcionalidade 1`: A possibilidade de Cadastrar ou Atualizar um lote de produtos por meio de um representante;

- `Funcionalidade 2`:A possibilidade de Consultar um produto;

- `Funcionalidade 3`: Listar os produtos por categoria;

- `Funcionalidade 4`: A possibilidade do comprador adicionar o produto ao carrinho;

- `Funcionalidade 5`: Mostrar os produtos no pedido;

- `Funcionalidade 6`: Modificar o status do pedido;

- `Funcionalidade 7`: Listar o produto em todos os lotes;

- `Funcionalidade 8`: Listar o produto em todos os lotes ordenados pelo lote, quantidade ou data de validade;

- `Funcionalidade 9`: Listar a quantidade total de produtos por armazém;

- `Funcionalidade 10`: Listar os lotes de um setor em um armazém ordenados pela data de vencimento;

- `Funcionalidade 11`: Listar os lotes dentro do prazo de validade solicitado que pertece a uma determinada categoria de produto, podendo 
  ser ordenada pela quantidade de forma crescente ou decrescente.


**Como a nossa equipe de desenvolvedores é muito preocupada com a qualidade do nosso serviço,
desenvolvemos testes unitários, garantindo que nossa aplicação funciona, é escalável e segura;**

A documentação das rotas está neste [arquivo](Desafio%203%20-%20Projeto%20Integrador.postman_collection.json)

