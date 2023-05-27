# Spring Boot - CRM application

This project is an implementation of a CRM using the Spring Boot framework.

## 💡 Concepts

### User

The user of the system. This can be either an administrator or a regular user, where privileges depend on their roles.

### Client

The main entity in the system. `Users` add `Clients`, so they can track their interaction with each `Client` separately.

### Pipeline

`Pipelines` are groups where we put different clients in order to better categorize them. Imagine a company has products
X, Y and Z. Ideally, each product would have its own pipeline where clients interested in that product would be put.

### Stage

`Stage` is just a subgroup of `Pipeline` entity. Each `Pipeline` can have multiple stages. Each `Stage` represents a
step in the process of buying a product. For example, Pipeline X can have "Lead in", "Lead contacted", "Lead contract
signed" stages.

This is useful, so we have knowledge of where each client is in the process.

### Deal

This is an entity that actually connects a `Client` to a `Stage`. It can hold additional information, such as value of
the deal, status, notes, ...

## 🏃‍♂️ How to run the project

1. `docker compose up`
2. `./gradlew build`
3. `./gradlew bootRun`

**The API** should now be available at `localhost:8080/api/*`.

**SwaggerUI** is available
at [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html).