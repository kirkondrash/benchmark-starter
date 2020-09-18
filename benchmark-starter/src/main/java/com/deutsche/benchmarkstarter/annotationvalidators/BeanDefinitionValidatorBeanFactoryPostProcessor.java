package com.deutsche.benchmarkstarter.annotationvalidators;

import com.deutsche.benchmarkstarter.annotations.RestBenchmark;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author Evgeny Borisov
 */
@Component
public class BeanDefinitionValidatorBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        String[] names = beanFactory.getBeanDefinitionNames();
//        System.out.println("names.length = " + names.length);
        for (String name : names) {
            BeanDefinition bd = beanFactory.getBeanDefinition(name);
            if (bd instanceof AnnotatedBeanDefinition){
                AnnotatedTypeMetadata metadata = ((AnnotatedBeanDefinition) bd).getMetadata();
                if (metadata.isAnnotated(RestBenchmark.class.getName()) && !metadata.isAnnotated(Controller.class.getName())){
                    throw new IllegalStateException(String.format("Bean of class %s is annotated with @RestBenchmark annotation but is not a @Controller!", bd.getBeanClassName()));
                }
            }
        }
    }
}
