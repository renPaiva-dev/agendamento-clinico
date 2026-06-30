# 🩺 Sistema de Agendamento Clínico

Uma aplicação **Full Stack** desenvolvida para facilitar o gerenciamento de pacientes e consultas em clínicas e unidades de saúde.

O sistema permite cadastrar pacientes, agendar consultas, atualizar informações e acompanhar os atendimentos através de uma API REST desenvolvida com **Spring Boot** e um frontend em **React**.

---

# 📌 Sobre o Projeto

O controle manual de consultas pode gerar dificuldades na organização dos atendimentos, perda de informações e retrabalho.

Este projeto foi desenvolvido para demonstrar uma solução simples para esse problema, permitindo o gerenciamento digital de pacientes e consultas por meio de uma aplicação web.

Além de atender às funcionalidades essenciais de um sistema de agendamento, o projeto foi criado para praticar conceitos de desenvolvimento Full Stack utilizando Java, Spring Boot, React e PostgreSQL.

---

# ✨ Funcionalidades

## 👤 Pacientes

* Cadastro de pacientes
* Listagem de pacientes
* Busca por paciente
* Atualização de dados
* Exclusão de pacientes

## 📅 Consultas

* Agendamento de consultas
* Associação da consulta a um paciente
* Atualização das informações da consulta
* Cancelamento de consultas
* Alteração do status da consulta
* Listagem de consultas
* Filtro por status
* Filtro por data

---

# 🛠 Tecnologias Utilizadas

### Backend

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Frontend

* React
* Vite
* JavaScript
* HTML5
* CSS3

### Ferramentas

* Git
* GitHub
* IntelliJ IDEA
* Visual Studio Code
* Postman

---

# 🏗 Arquitetura

O backend foi desenvolvido utilizando arquitetura em camadas.

```text
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
PostgreSQL
```

Essa organização facilita a manutenção do código, separa responsabilidades e torna a aplicação mais escalável.

---

# 🗄 Modelo de Dados

## Paciente

| Campo          | Tipo      |
| -------------- | --------- |
| id             | Long      |
| nome           | String    |
| cpf            | String    |
| telefone       | String    |
| email          | String    |
| dataNascimento | LocalDate |

---

## Consulta

| Campo       | Tipo          |
| ----------- | ------------- |
| id          | Long          |
| dataHora    | LocalDateTime |
| status      | Enum          |
| observacoes | String        |
| paciente    | Paciente      |

---

### Relacionamento

```text
Paciente (1) ----------- (N) Consulta
```

Um paciente pode possuir várias consultas.

Cada consulta pertence a um único paciente.

---

# 📁 Estrutura do Projeto

```text
agendamento-clinico/

├── backend/
│   ├── src/
│   ├── pom.xml
│   └── .gitignore
│
├── frontend/
│   ├── src/
│   ├── package.json
│   ├── vite.config.js
│   └── .gitignore
│
└── README.md
```

---

# 🌐 Endpoints da API

## Pacientes

| Método | Endpoint        | Descrição                |
| ------ | --------------- | ------------------------ |
| GET    | /pacientes      | Lista todos os pacientes |
| GET    | /pacientes/{id} | Busca um paciente        |
| POST   | /pacientes      | Cadastra um paciente     |
| PUT    | /pacientes/{id} | Atualiza um paciente     |
| DELETE | /pacientes/{id} | Remove um paciente       |

---

## Consultas

| Método | Endpoint        | Descrição                |
| ------ | --------------- | ------------------------ |
| GET    | /consultas      | Lista todas as consultas |
| GET    | /consultas/{id} | Busca uma consulta       |
| POST   | /consultas      | Agenda uma consulta      |
| PUT    | /consultas/{id} | Atualiza uma consulta    |
| DELETE | /consultas/{id} | Remove uma consulta      |

### Filtros disponíveis

Buscar consultas por status:

```http
GET /consultas?status=AGENDADA
```

Buscar consultas por data:

```http
GET /consultas?data=2026-06-30
```

---

# 🚀 Como Executar o Projeto

## 1. Clone o repositório

```bash
git clone https://github.com/SEU-USUARIO/agendamento-clinico.git
```

Entre na pasta:

```bash
cd agendamento-clinico
```

---

# ⚙ Configuração do Banco de Dados

Crie um banco PostgreSQL chamado:

```text
agendamento_clinico
```

---

# ⚙ Configuração do Backend

Entre na pasta do backend:

```bash
cd backend
```

Crie o arquivo:

```text
src/main/resources/application.properties
```

Adicione as configurações do seu banco PostgreSQL:

```properties
spring.application.name=agendamento-clinico

spring.datasource.url=jdbc:postgresql://localhost:5432/agendamento_clinico
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

---

# 💻 Configuração do Frontend

Entre na pasta:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute:

```bash
npm run dev
```

O frontend ficará disponível em:

```text
http://localhost:5173
```

---

# 🔒 Segurança

Por questões de segurança, o arquivo `application.properties` não faz parte deste repositório.

Antes de executar a aplicação, crie esse arquivo em:

```text
backend/src/main/resources/application.properties
```

e informe suas próprias credenciais do PostgreSQL.

---

# 📸 Demonstração

> Adicione aqui capturas de tela da aplicação.

Sugestão:

* Tela inicial
* Cadastro de pacientes
* Lista de pacientes
* Agendamento de consultas
* Lista de consultas

---

# 🚀 Melhorias Futuras

* Autenticação com JWT
* Controle de usuários
* Dashboard com indicadores
* Busca por nome do paciente
* Paginação
* Swagger/OpenAPI
* Testes automatizados
* Deploy da aplicação

---

# 📚 Conceitos Aplicados

* API REST
* CRUD
* Arquitetura em Camadas
* Spring Data JPA
* Relacionamento entre entidades
* Integração Frontend ↔ Backend
* Consumo de API com React
* Persistência de dados com PostgreSQL
* Versionamento com Git e GitHub

---

# 👨‍💻 Autor

**Renato Belarmino**

Projeto desenvolvido para fins de estudo, prática e aprimoramento de habilidades em desenvolvimento Full Stack utilizando Java, Spring Boot, React e PostgreSQL.
