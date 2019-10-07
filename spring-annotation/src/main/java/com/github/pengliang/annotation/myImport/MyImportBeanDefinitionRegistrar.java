package com.github.pengliang.annotation.myImport;

import com.github.pengliang.annotation.bean.Blue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author pengliang  2019-03-15 21:56
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata 获取当前类注解
     * @param registry bean注入器，可以手动注入bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean isExistBook = registry.containsBeanDefinition("com.github.pengliang.annotation.bean.Book");
        boolean isExistRead = registry.containsBeanDefinition("com.github.pengliang.annotation.bean.Read");
        if (isExistBook && isExistRead){
            registry.registerBeanDefinition("blue",new RootBeanDefinition(Blue.class));
        }
    }
}
