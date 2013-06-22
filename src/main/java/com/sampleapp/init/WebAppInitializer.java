package com.sampleapp.init;

import com.sampleapp.db.repository.PersistenceConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{PersistenceConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfiguration.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/sampleapp/*"};
    }
}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sampleapp.mvc.controller","com.sampleapp.service"})
class WebMvcConfiguration  {

}