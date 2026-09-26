# Spring Web MVC (JSP + Hibernate)

> A classic XML-configured Spring MVC web app: `DispatcherServlet`, controllers, JSP views, and a signup form saved to MySQL with Hibernate.

## What it teaches
- Front controller: `DispatcherServlet` in `web.xml`, loading `spring-servlet.xml` by naming convention
- `@Controller` + `@RequestMapping`, returning a view name or a `ModelAndView`
- Passing data to JSP with `Model` / `ModelAndView`, JSTL `<c:forEach>`, EL `${...}`
- Form binding with `@ModelAttribute`
- Layers: controller -> `@Service` -> `@Repository` -> `HibernateTemplate`, with `@Transactional`
- `InternalResourceViewResolver` (prefix `/WEB-INF/views/`, suffix `.jsp`)

## Run it
Needs MySQL (the `springmvc` database is created automatically) and Tomcat 10.1.
Set `DB_USERNAME` (defaults to `root`) and `DB_PASSWORD` in the environment Tomcat runs in (in Eclipse: *Server > Open launch configuration > Environment*).

```bash
mvn package            # builds target/springmvc.war
```

Deploy `target/springmvc.war` to Tomcat 10.1's `webapps/`, or in Eclipse: *Servers view > Add and Remove... > springmvc*.
Then open `http://localhost:8080/springmvc/home`, `/about`, `/help` and `/contact` (submitting the contact form saves a `User`).

## Read the code in this order
1. `src/main/webapp/WEB-INF/web.xml` - `DispatcherServlet` mapped to `/`
2. `src/main/webapp/WEB-INF/spring-servlet.xml` - component scan, view resolver, datasource, SessionFactory, HibernateTemplate, transactions
3. `src/main/java/springmvc/controller/HomeController.java` - `Model`, `ModelAndView`, lists to JSP
4. `src/main/webapp/WEB-INF/views/index.jsp`, `help.jsp` - EL + JSTL
5. `src/main/java/springmvc/controller/ContactController.java` - GET form, POST `@ModelAttribute`
6. `service/UserService.java`, `dao/UserDao.java`, `model/User.java`

## Revision notes
- Request flow: browser -> `DispatcherServlet` -> handler mapping -> controller -> view name -> `ViewResolver` -> JSP.
- `@ModelAttribute User user` binds form fields to properties by name (`email`, `userName`, `password`) - no `request.getParameter` needed (see the commented-out servlet version).
- `Model` vs `ModelAndView`: same data, but `ModelAndView` also carries the view name.
- `web.xml` uses the Jakarta EE 6.0 schema, so EL is on; with the old 2.3 DTD EL was ignored unless `isELIgnored="false"`.
- Tomcat 10+ is Jakarta EE: use the `jakarta.*` JSTL (`org.glassfish.web:jakarta.servlet.jsp.jstl`), not `javax` `jstl:jstl`.
- `HibernateTemplate.save` needs a transaction -> `HibernateTransactionManager` + `<tx:annotation-driven/>` + `@Transactional` on `UserDao.saveUser`.
- Spring 6 `HibernateTemplate` needs Hibernate 5, hence `hibernate-core-jakarta` 5.6.15.
- `User.toString()` deliberately leaves out the password. The password is still stored in plain text, so this is not production-safe.

## Status
🚧 Partial. It builds a WAR, and every bean class in `spring-servlet.xml` resolves. The Spring context was fixed in this cleanup (datasource `ds`, transaction manager, real `orm.hibernate5` classes, `@Service`/`@Repository`), but it was **not** run against a real MySQL + Tomcat. Expect to tweak something the first time you deploy.
