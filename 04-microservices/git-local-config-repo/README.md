# Config Repo (local)

The property files that [spring-cloud-config-server](../spring-cloud-config-server) serves, read through its `native` profile from `file:../git-local-config-repo`.

| File | Profile | minimum / maximum |
|---|---|---|
| `limit-service-microservices.properties` | default | 4 / 996 |
| `limit-service-microservices-dev.properties` | `dev` | 5 / 995 |
| `limit-service-microservices-qa.properties` | `qa` | 6 / 994 |

- The file name is `{spring.application.name}[-{profile}].properties`. The app name here is `limit-service-microservices`, from [limits-service](../limits-service).
- For the same key, the profile file overrides the default file.
- limits-service asks for the `dev` profile (`spring.cloud.config.profile=dev`), so `/limits` returns 5 / 995.
- Despite the folder name, this isn't a git repo; the native backend doesn't need one. To use the git backend instead, run `git init` here and switch the server to `spring.cloud.config.server.git.uri`.
