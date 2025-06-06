Projekt realizuje zarządzanie użytkownikami i grupami poprzez REST API oparte na Spring Boot. Dane użytkowników i grup są przechowywane w bazie PostgreSQL, z wykorzystaniem ORM (Hibernate). Programowanie reaktywne (Spring WebFlux) zapewnia obsługę asynchronicznych operacji na danych. Cała aplikacja działa w kontenerach Docker, a logowanie metod serwisowych jest realizowane za pomocą aspektów AOP.
2. Budowanie projektu

W katalogu głównym projektu uruchom:

```
mvn clean install
```

2. Uruchomienie przez Docker Compose

Następnie uruchom aplikację i bazę danych PostgreSQL:

```
docker-compose up --build
```

Dostępne serwisy:
- Aplikacja: http://localhost:8080
- Baza danych PostgreSQL: localhost:5432 (user: user, password: pass, database: usersdb)

3. Dostępne Endpointy

Users (Użytkownicy)
- POST /users – Utworzenie nowego użytkownika

```
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"username":"john", "password":"secret", "email":"john@example.com", "groups":[]}'
```

- GET /users/{id} – Pobranie użytkownika po ID

```
curl http://localhost:8080/users/1
```

Groups (Grupy użytkowników)
- POST /groups – Utworzenie nowej grupy

```
curl -X POST http://localhost:8080/groups \
-H "Content-Type: application/json" \
-d '{"name":"admin"}'
```

- GET /groups/{id} – Pobranie grupy po ID

```
curl http://localhost:8080/groups/1
```

4. Technologie użyte w projekcie

- Java 21
- Spring Boot 3.x
- Spring Data JPA
- Spring WebFlux
- Spring AOP
- PostgreSQL
- Maven
- Docker / Docker Compose

5. Testy
```
mvn test
```

6. Diagram:
   Klient -> REST API (Spring Boot)
   -> UserService, GroupService
   -> JPA/Hibernate
   -> PostgreSQL (Docker)