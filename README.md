# 📦 API de Gerenciamento de Produtos

Esta é uma API RESTful desenvolvida com **Spring Boot**, projetada para realizar operações de **CRUD (Create, Read, Update, Delete)** sobre uma entidade de produto. A aplicação utiliza **DTOs para requisições e respostas**, um **serviço com DAO genérico** e está documentada com **Swagger OpenAPI 3**.

---

## ✨ Funcionalidades

- ✅ Criar novo produto
- 📝 Atualizar produto existente
- 🔍 Listar todos os produtos
- 🔎 Buscar produto por ID
- ❌ Deletar produto por ID

---

## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Jakarta Validation
- Swagger OpenAPI (Springdoc)
- Lombok
- DAO Genérico com injeção por fábrica (`DAOFactory`)

---

## 📁 Estrutura do Projeto

```
com.project.desafio
├── config                # Configurações
├── controller            # Controladores REST
├── dao                   # DAO Genérico
│   ├── factory           # Fábrica de DAOs
│   ├── impl              # Implementação dos DAOs
│   └── GenericDAO        # Interface genérica do DAO 
├── dto
│   ├── request           # DTOs de entrada
│   └── response          # DTOs de saída
├── entity                # Entidades de domínio
├── exceptions            # Exceptions customizadas
└── service
    ├── impl              # Implementações dos serviços
    └── ProdutoService    # Interface de serviço
```

---

## 🚀 Endpoints da API

Base URL: `/v1/produtos`

### 🔸 Criar Produto

- **POST** `/v1/produtos`
- **Body**:
```json
{
  "nome": "Celular Samsung",
  "descricao": "Aparelho celular",
  "preco": 1000.00,
  "quantidadeEstoque": 200
}
```
- **Response**: `201 Created`

---

### 🔸 Atualizar Produto

- **PUT** `/v1/produtos`
- **Body**:
```json
{
  "id": 1,
  "nome": "Celular Samsung Galaxy",
  "descricao": "Novo modelo atualizado",
  "preco": 1200.00,
  "quantidadeEstoque": 180
}
```
- **Response**: `200 OK`

---

### 🔸 Listar Todos os Produtos

- **GET** `/v1/produtos`
- **Response**: `200 OK`

---

### 🔸 Buscar Produto por ID

- **GET** `/v1/produtos/{id}`
- **Response**: `200 OK`

---

### 🔸 Deletar Produto

- **DELETE** `/v1/produtos/{id}`
- **Response**: `204 No Content`

---

## 📄 Documentação Swagger

A documentação interativa da API pode ser acessada em:

```
http://localhost:8080/swagger-ui.html
```

---

## 🧪 Validações

- `nome` e `descricao`: não podem ser vazios.
- `preco`: obrigatório e maior que zero.
- `quantidadeEstoque`: obrigatório.
- `id`: obrigatório para atualização e deleção.

---

## ❌ Tratamento de Erros

| Código HTTP | Descrição                          |
|-------------|------------------------------------|
| 400         | Erros de validação                 |
| 404         | Produto não encontrado             |
| 500         | Erro interno do servidor           |

---

## 🧱 Como Rodar o Projeto

1. Clone o repositório:
```bash
git clone https://github.com/claytoncastro/desafio.git
```

2. Navegue até o diretório do projeto:
```bash
cd desafio
```

3. Compile o projeto:
```bash
./mvnw clean install
```

4. Execute a aplicação:
```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📌 Requisitos

- Java 17+
- Maven 3.8+
- Banco de dados configurado (se aplicável)

