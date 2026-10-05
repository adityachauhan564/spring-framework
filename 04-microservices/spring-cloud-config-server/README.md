# Spring Cloud Config Server

> A central configuration server (port 8888) that gives property files to the other microservices.

## Why it matters
With ten services you would have ten sets of `application.properties`, copied onto every server. Changing one value means rebuilding and deploying again. A config server keeps all the config in **one place** (a folder or a git repo), and each service fetches its own part at startup.

## What it teaches
- `@EnableConfigServer`: one annotation turns a Boot app into a config server
- Backends (where the files are kept): **native** (a folder on disk, used here) vs **git** (the course default)
- How clients ask for config: `/{application}/{profile}`

## Run it
**Run it from this folder**, because the default config path is relative: `file:../git-local-config-repo`.
```bash
./mvnw spring-boot:run          # Windows: mvnw.cmd spring-boot:run

curl localhost:8888/limit-service-microservices/default
curl localhost:8888/limit-service-microservices/dev
curl localhost:8888/limit-service-microservices/qa
```
Started from somewhere else (for example an IDE)? Then point it at the folder: `CONFIG_REPO=file:/absolute/path/to/git-local-config-repo`.

```bash
./mvnw test     # ConfigServerTest: the dev response lists the -dev file first, then the default file
```

## Read the code in this order
1. `src/main/java/com/example/udemy/config_server/SpringCloudConfigServerApplication.java`: `@EnableConfigServer`
2. `src/main/resources/application.properties`: port 8888, native profile, `${CONFIG_REPO:...}` search location
3. `../git-local-config-repo/*.properties`: the files it serves
4. `src/test/java/com/example/udemy/config_server/ConfigServerTest.java`

## Revision notes
- URL pattern: `/{application}/{profile}[/{label}]`. The JSON lists the property sources **in order of priority**: the profile file (`-dev`) comes first, and it wins over the base file for the same key.
- `spring.profiles.active=native` + `spring.cloud.config.server.native.search-locations` serves plain files, with no git needed.
- The git backend (`spring.cloud.config.server.git.uri`) adds history, and `label` = branch. The folder must really be a git repo.
- `${CONFIG_REPO:file:../git-local-config-repo}` = "use the environment variable if it is set, otherwise this default". It is the same pattern used for DB passwords.
- The server has no Actuator. So `/actuator/health` is read as a request for the config of an app called `actuator` with profile `health`. That is why `start-all` checks that the server is ready by fetching a real config file instead.
- Clients connect with `spring.config.import=optional:configserver:http://localhost:8888`.

## Status
✅ **Working.** 2 tests pass. The main class was written again during cleanup (the original was never committed), and the server was switched from an absolute Windows git path to the relative native folder.
