package com.github.pengliang.annotation.bean.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author pengliang  2019-03-19 16:56
 */
@Component
public class CycleGreed implements BeanPostProcessor {

    /**
     * @param bean     刚创建的实例
     * @param beanName 实例在容器中的名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CycleGreed->Before");
        System.out.println(beanName + "：" + bean.getClass());
        System.out.println("---------------------------------");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CycleGreed->After");
        System.out.println(beanName + "：" + bean.getClass());
        System.out.println("---------------------------------");
        return bean;
    }
}
