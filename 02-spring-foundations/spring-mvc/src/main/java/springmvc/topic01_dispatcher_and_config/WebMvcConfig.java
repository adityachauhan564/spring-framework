package springmvc.topic01_dispatcher_and_config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Topic    : How a request reaches your code
 * Key idea : browser -> DispatcherServlet (web.xml) -> handler mapping finds the @Controller method
 *            -> the method returns a VIEW NAME such as "index"
 *            -> the ViewResolver turns it into /WEB-INF/views/index.jsp -> HTML back to the browser.
 *
 *   @EnableWebMvc   - switches on @Controller / @GetMapping support, JSON (Jackson) and
 *                     validation (Hibernate Validator) - the XML form was <mvc:annotation-driven/>
 *   @ComponentScan  - finds every controller, service, repository and the other @Configuration
 *                     classes under the springmvc package
 *   jsp(...)        - the InternalResourceViewResolver that used to be a <bean> in spring-servlet.xml
 *
 * JSPs sit under WEB-INF so a browser can't open them directly - only through a controller.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springmvc")
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");   // "index" -> /WEB-INF/views/index.jsp
    }
}
