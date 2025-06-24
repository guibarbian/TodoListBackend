
# TodoList Backend ✅

## O Projeto 📊

Este é o backend para uma aplicação de gerenciamento de tarefas

## Tecnologias Utilizadas 🧭

- **SpringBoot** - Framework principal
- **Spring Data JPA** - Para interações com o banco de dados
- **PostgreSQL** - Banco de dados
- **Swagger** - Para documentação da API
- **Maven** - Gerenciador de dependências
- **JUnit** - Para testes unitários
- **MockMVC** - Para testes de integração

## Pré-requisitos

- Java 8 ou superior
- Maven
- IDE(recomendado IntelliJ ou VSCode)

## Como rodar o projeto

1. Copie o repositório
```bash
git clone https://github.com/guibarbian/TodoListBackend.git
cd TodoListBackend
```
2. Instale as dependências
```bash
mvn install
```
3. Rode a aplicação
```bash
mvn spring-boot:run
```
A aplicação vai ser executada em http://localhost:8080

Você pode usar algum cliente de API como Postman ou Insomnia para testar os endpoints manualmente

# Endpoints
## Tasks

Esta API tem os seguintes Endpoints para organização de tarefas

| Método | Endpoint             | Descrição                         |
|--------|----------------------|-----------------------------------|
| GET    | `/api/v1/tasks`      | Retorna todas as Tasks existentes |
| POST   | `/api/v1/tasks`      | Cria uma Task                     |
| PUT    | `/api/v1/tasks/{id}` | Atualiza uma Task                 |
| DELETE | `/api/v1/tasks/{id}` | Deleta uma Task                   |

## 

Para criar ou atualizar uma tarefa, você deve enviar um corpo JSON com os seguintes atributos:
```json
{
  "titulo": "Título da Tarefa",
  "concluida": false
}
```

# Desenvolvido com ⚙

- **IntelliJ IDEA**

# Autor ✏

- Guilherme A. Barbian 