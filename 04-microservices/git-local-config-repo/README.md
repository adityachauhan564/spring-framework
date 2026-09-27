# Config Repo (local)

The property files that [spring-cloud-config-server](../spring-cloud-config-server) serves, read through its `native` profile from `file:../git-local-config-repo`.

| File | Profile | minimum / maximum |
|---|---|---|
| `limit-service-microservices.properties` | default | 4 / 996 |
| `limit-service-microservices-dev.properties` | `dev` | 5 / 995 |
| `limit-service-microservices-qa.properties` | `qa` | 6 / 994 |

- The file name is `{spring.application.name}[-{profile}].properties`. The app name here is `limit-service-microservices`, from [limits-service](../limits-service).
- For the same key, the profile file overrides the default file.
- limits-service runs with the `dev` profile (`spring.profiles.active=dev`), so `/limits` returns 5 / 995.
- Edit a value here while everything runs, then `curl -X POST localhost:8080/actuator/refresh`: limits-service picks it up without a restart (step 2 of the [stage walkthrough](../README.md#walkthrough-try-every-pattern-with-the-system-running)). The server reads the files on every request, so it never needs a restart either.
- Only limits-service has files here. currency-exchange and currency-conversion also import config (`optional:`), find none, and use their own `application.properties`. Add `currency-exchange.properties` here to try it.
- Despite the folder name, this isn't a git repo; the native backend doesn't need one. To use the git backend instead, run `git init` here and switch the server to `spring.cloud.config.server.git.uri`.
