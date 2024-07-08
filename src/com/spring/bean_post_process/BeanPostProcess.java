package com.spring.bean_post_process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import static com.spring.bean_post_process.BeanLogCheck.*;

public class BeanPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        beforeInitDatabaseHelper(bean);
        beforeInitEmployee(bean);
        beforeInitService(bean);
        beforeInitController(bean);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        afterInitDatabaseHelper(bean);
        afterInitEmployee(bean);
        afterInitService(bean);
        afterInitController(bean);
        return bean;
    }
}
