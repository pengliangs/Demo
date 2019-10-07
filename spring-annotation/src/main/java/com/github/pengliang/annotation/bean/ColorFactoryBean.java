package com.github.pengliang.annotation.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author pengliang  2019-03-19 15:10
 */
public class ColorFactoryBean implements FactoryBean<Color> {

    /**
     * 创建bean
     * @return
     * @throws Exception
     */
    @Override
    public Color getObject() throws Exception {
        return new Color();
    }

    /**
     * bean类型
     * @return
     */
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }

    /**
     * 是否单利
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
