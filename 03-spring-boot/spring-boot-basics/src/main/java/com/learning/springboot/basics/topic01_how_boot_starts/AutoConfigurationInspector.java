package com.learning.springboot.basics.topic01_how_boot_starts;

import java.util.Map;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport.ConditionAndOutcomes;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.learning.springboot.basics.BasicsApplication;

/*
 * Topic    : How Spring Boot starts - @SpringBootApplication and auto-configuration
 * Key idea : Auto-configuration = Boot sets things up for you.
 *            - Boot looks at the CLASSPATH (the libraries in your project) and your settings.
 *            - Then it creates the beans you would otherwise write by hand
 *              (a DispatcherServlet, a JSON mapper, a DataSource...).
 *            - Like a new flat that comes "fully furnished": you only bring what you want different.
 *            - Every auto-configuration class has conditions, such as
 *              @ConditionalOnClass ("only if this library is present") and
 *              @ConditionalOnMissingBean ("only if you did not make one yourself" - so your own
 *              bean always wins).
 *            - The ConditionEvaluationReport writes down every one of these decisions.
 * Run      : ./mvnw spring-boot:run            (this runner prints first)
 *            ./mvnw spring-boot:run -Dspring-boot.run.arguments=--debug   (the FULL report)
 *            or open http://localhost:8080/actuator/conditions while the app runs
 * Compare  : 02-spring-foundations/spring-mvc/.../WebMvcConfig.java - there you set all this up by hand.
 */
// Not named "AutoConfigurationReport" on purpose: Boot already has a bean called "autoConfigurationReport"
// (the report itself). A @Component with the same default name would silently never be created.
@Component
@Order(1)
public class AutoConfigurationInspector implements ApplicationRunner {

    private final ConditionEvaluationReport report;

    public AutoConfigurationInspector(ConfigurableListableBeanFactory beanFactory) {
        this.report = ConditionEvaluationReport.get(beanFactory);
    }

    @Override
    public void run(ApplicationArguments args) {
        System.out.println("\n=== topic01: how Boot starts ===");
        System.out.println("@SpringBootApplication on BasicsApplication includes:"
                + " @SpringBootConfiguration=" + has(SpringBootConfiguration.class)
                + ", @EnableAutoConfiguration=" + has(EnableAutoConfiguration.class)
                + ", @ComponentScan=" + has(ComponentScan.class));

        Map<String, ConditionAndOutcomes> outcomes = report.getConditionAndOutcomesBySource();
        long matched = outcomes.values().stream().filter(ConditionAndOutcomes::isFullMatch).count();
        System.out.println("Auto-configuration decisions: " + outcomes.size() + " checked, "
                + matched + " applied, " + (outcomes.size() - matched) + " skipped");

        show(outcomes, "DispatcherServletAutoConfiguration");   // web starter on the classpath -> applied
        show(outcomes, "JacksonAutoConfiguration");             // JSON library on the classpath -> applied
        show(outcomes, "BeansEndpointAutoConfiguration");       // /actuator/beans is not in the exposure list -> skipped
        show(outcomes, "DataSourceAutoConfiguration");          // no database library at all -> not even checked
    }

    private static boolean has(Class<? extends java.lang.annotation.Annotation> annotation) {
        return AnnotatedElementUtils.hasAnnotation(BasicsApplication.class, annotation);
    }

    private static void show(Map<String, ConditionAndOutcomes> outcomes, String simpleName) {
        outcomes.entrySet().stream()
                .filter(entry -> entry.getKey().endsWith("." + simpleName))
                .findFirst()
                .ifPresentOrElse(
                        entry -> System.out.println("  " + simpleName + ": "
                                + (entry.getValue().isFullMatch() ? "APPLIED" : "SKIPPED (at least partly)")),
                        () -> System.out.println("  " + simpleName + ": not considered (its library isn't on the classpath)"));
    }
}
