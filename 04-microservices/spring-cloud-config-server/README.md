# Spring Cloud Config Server

> Central configuration server (port 8888) that serves property files to the other microservices.

## What it teaches
- `@EnableConfigServer`: turns a Boot app into a config server
- Backends: **native** (a folder on disk, used here) vs **git** (the course default)
- How clients ask for config: `/{application}/{profile}`

## Run it
**Run it from this folder.** The config path is relative to where you start it: `file:../git-local-config-repo`.
```bash
cd 04-microservices/spring-cloud-config-server
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run

curl localhost:8888/limit-service-microservices/default
curl localhost:8888/limit-service-microservices/dev
curl localhost:8888/limit-service-microservices/qa
```

## Read the code in this order
1. `src/main/java/com/example/udemy/config_server/SpringCloudConfigServerApplication.java`: `@EnableConfigServer`
2. `src/main/resources/application.properties`: port 8888, native profile, search location
3. `../git-local-config-repo/*.properties`: the files it serves

## Revision notes
- URL pattern: `/{application}/{profile}[/{label}]`. Responses are JSON listing the property sources in precedence order.
- The profile-specific file (`-dev`) wins over the base file for the same key.
- `spring.profiles.active=native` together with `spring.cloud.config.server.native.search-locations` serves plain files, with no git needed.
- The git backend (`spring.cloud.config.server.git.uri`) gives you config history and `label` = branch. The folder must actually be a git repo.
- Clients connect with `spring.config.import=optional:configserver:http://localhost:8888`.

## Status
✅ **Working.** The main class was **recreated** during cleanup: the original source was never committed. The server was switched from an absolute Windows git path to the relative native folder. Compiles (Boot 4.0.2 / Spring Cloud 2025.1.0).
