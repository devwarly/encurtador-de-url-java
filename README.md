# 🚀 API Encurtador de URL (Java + Spring Boot + Docker)

Este projeto é uma API REST para encurtamento de URLs, desenvolvida com **Java 21**, **Spring Boot 4** e **PostgreSQL**. A aplicação é totalmente "dockerizada", facilitando a configuração do ambiente de desenvolvimento.

## 🛠️ Tecnologias Utilizadas

* **Java 21** (LTS)
* **Spring Boot 4.0.5**
* **Spring Data JPA** (Hibernate)
* **PostgreSQL** (Banco de dados relacional)
* **Lombok** (Produtividade)
* **Docker & Docker Compose** (Containerização)
* **Maven** (Gerenciamento de dependências)

---

## 📋 Pré-requisitos

Antes de começar, você precisará ter instalado em sua máquina:
* [Docker](https://www.docker.com/products/docker-desktop/)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Git](https://git-scm.com/)

---

## ⚙️ Configuração do Ambiente

Como este é um repositório público, os arquivos de configuração contêm variáveis que você deve preencher de acordo com sua preferência.

### 1. Clonar o Repositório
```bash
git clone https://github.com/devwarly/encurtador-de-url-java.git
cd encurtador-de-url-java
```

### 2. Configurar o `application.properties`
Edite o arquivo em `src/main/resources/application.properties` para garantir que a API se comunique com o container do banco:

```properties
# Configurações de conexão (O host 'db_shortener' deve ser o mesmo do docker-compose)
spring.datasource.url=jdbc:postgresql://db_shortener:5432/NOME_DO_SEU_BANCO
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Configurar o `docker-compose.yml`
Crie ou edite o arquivo `docker-compose.yml` na raiz do projeto com suas credenciais:

```yaml
services:
  db_shortener: # Nome do serviço usado no host do application.properties
    image: postgres:15
    container_name: postgres_db
    environment:
      POSTGRES_USER: SEU_USUARIO
      POSTGRES_PASSWORD: SUA_SENHA
      POSTGRES_DB: NOME_DO_SEU_BANCO
    ports:
      - "7000:5432" # Porta externa:interna
    volumes:
      - pgdata:/var/lib/postgresql/data

  api_shortener:
    build: .
    container_name: spring_api
    ports:
      - "8080:8080"
    depends_on:
      - db_shortener

  pgadmin_shortener:
    image: dpage/pgadmin4
    container_name: pgadmin_gui
    environment:
      PGADMIN_DEFAULT_EMAIL: seu@email.com
      PGADMIN_DEFAULT_PASSWORD: sua_senha_pgadmin
    ports:
      - "5050:80"

volumes:
  pgdata:
```

---

## 🚀 Como Executar

Com o Docker Desktop rodando, execute o comando abaixo na pasta raiz:

```bash
docker-compose up -d --build
```

Este comando irá:
1.  Compilar o projeto Java e gerar a imagem da API.
2.  Subir o container do PostgreSQL.
3.  Subir o container do PGAdmin (Gerenciador de banco).

Para acompanhar os logs da API:
```bash
docker logs -f spring_api
```

---

## 📡 Endpoints da API

### 1. Encurtar URL
* **Método:** `POST`
* **URL:** `http://localhost:8080/api`
* **Corpo (JSON):**
```json
{
  "urlOriginal": "https://www.google.com"
}
```

### 2. Redirecionar
* **Método:** `GET`
* **URL:** `http://localhost:8080/api/{codigo}`
* **Ação:** Redireciona o usuário para a URL original salva no banco.

---

## 🗄️ Acessando o Banco de Dados (PGAdmin)

1.  Acesse `http://localhost:5050` no seu navegador.
2.  Faça login com as credenciais definidas no `docker-compose.yml`.
3.  Para conectar ao banco, use o Host Name: `db_shortener`.

---

## ✒️ Autor

* **Warly Martins Souza** - [GitHub](https://github.com/devwarly) - [LinkedIn](https://linkedin.com/in/warly-dev)

