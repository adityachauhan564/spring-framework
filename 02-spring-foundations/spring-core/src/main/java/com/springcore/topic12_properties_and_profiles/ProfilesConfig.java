package com.springcore.topic12_properties_and_profiles;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/* @PropertySource loads the file into the Environment, so ${...} and Environment.getProperty can read it. */
@Configuration
@ComponentScan
@PropertySource("classpath:com/springcore/topic12_properties_and_profiles/app.properties")
public class ProfilesConfig {
}
