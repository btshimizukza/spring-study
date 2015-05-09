package com.github.kazuki43zoo.container;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationWithJavaMetadataTest {

    @Test
    public void configure() {
        ConfigurableApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.registerShutdownHook();
        TestBean testBean = context.getBean("testBean", TestBean.class);
        testBean.print();
    }

}
