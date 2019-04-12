package com.javaguru.shoppinglist.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.javaguru.shoppinglist"})
public class SpringWebMVCConfig extends WebMvcConfigurerAdapter {

}
