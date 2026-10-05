package springmvc.topic01_dispatcher_and_config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Topic    : How a request reaches your code
 * Key idea : This is the journey of one request, step by step:
 *            browser -> DispatcherServlet (web.xml) -> handler mapping finds the right @Controller method
 *            -> the method returns a VIEW NAME such as "index"
 *            -> the ViewResolver turns that name into /WEB-INF/views/index.jsp -> HTML goes back to the browser.
 *            Like a hotel reception: the receptionist (DispatcherServlet) takes every guest's request
 *            and sends it to the right department (controller).
 *
 *   @EnableWebMvc   - switches on support for @Controller / @GetMapping, JSON (Jackson) and
 *                     validation (Hibernate Validator). In XML this was <mvc:annotation-driven/>
 *   @ComponentScan  - finds every controller, service, repository and the other @Configuration
 *                     classes under the springmvc package
 *   jsp(...)        - the InternalResourceViewResolver, which used to be a <bean> in spring-servlet.xml
 *
 * JSPs are kept under WEB-INF, so a browser cannot open them directly - only through a controller.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springmvc")
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");   // adds a prefix and a suffix: "index" -> /WEB-INF/views/index.jsp
    }
}
