package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigurationMetadataTest {

    @Test
    public void configureUsingJavaConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);
        context.registerShutdownHook();
        TestBean testBean = context.getBean("testBean", TestBean.class);
        assertThat(testBean.getMessage(), is("Hello World by TestBean"));
    }

    @Test
    public void configureUsingXml() {
        ConfigurableApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        context.registerShutdownHook();
        TestBean testBean = context.getBean("testBean", TestBean.class);
        assertThat(testBean.getMessage(), is("Hello World by TestBean"));
    }


    @Test
    public void configureUsingAnnotationConfig() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AnnotationConfig.class);
        context.registerShutdownHook();
        AnnotatedTestBean testBean = context.getBean("annotatedTestBean", AnnotatedTestBean.class);
        assertThat(testBean.getMessage(), is("Hello World by AnnotatedTestBean"));
    }


    @Configuration
    static class Config {
        @Bean
        TestBean testBean() {
            return new TestBean();
        }
    }

    @Configuration
    @ComponentScan(excludeFilters = @ComponentScan.Filter(Configuration.class))
    static class AnnotationConfig {
        @Bean
        ChildBean childBean() {
            return new ChildBean();
        }
    }

}
