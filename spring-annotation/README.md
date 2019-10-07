# Spring注解开发

* @Configuration

    标记当前类为一个组件配置类 ，好比是xml配置文件

* [@Bean](#Bean生命周期)

    将当前标记方法返回对象放入IOC容器中管理<br>对应XML:`<bean>`标签

* @Scope

    对象作用域，默认：singleton单利 
 
    singleton：一个Spring容器中只有一个Bean的实例，全容器共享一个实例；在Ioc容器创建的时候就会创建实例到容器中

    prototype: Ioc容器创建不会立马创建实例，每次调用新创建一个Bean的实例 

    request:Web项目中，给每一个http request新建一个Bean实例

    session:Web项目中，给每一个http session新建一个Bean实例<br>

* @ComponentScan

    扫描将标注`@Repository`、`@Component`、`@Service`、`@Controller` 扫描组件，添加到容器中
 
    JDK1.8 @Repeatable(ComponentScans.class) 可以重复标记 
 
    不是1.8如果要重复标记可以使用 `@ComponentScans`  对应XML： `<context:component-scan>`

* @Lazy

    懒加载，第一次获取使用的时候创建加载

* @Conditional

    Spring4.0中的新特性，在Spring Boot底层大量使用，按照一定的条件进行判断，满足条件给容器中注册bean

    使用该注解必须实现 `Condition` 接口然后在 `matches` 方法进行条件过滤


* @Import

    导入一个外部组件到Ioc容器，如：在使用第三方library的时候没有标记 `@Component`, `@Service` 注解的时候时不会将Bean放到容
器中管理的，这个时我们可以使用 `@Import` 进行导入一个外部组件

    ImportSelector接口：selectImports方法返回需要导入类的全类名数组

    ImportBeanDefinitionRegistrar接口：registerBeanDefinitions方法


* @Value

    属性赋值

* @PropertySource

    读取外部配置文件 k/v 保存到运行变量中，对应xml：`<context:property-placeholder>`

* @Autowired

    自动装配bean实例，默认根据类型装配

* Qualifier

    在使用自动装配 @Autowired 时存在多个实例，可以通过 @Qualifier 指定名称查找

* @Primary

    在使用自动装配时出现多个实例，可以通过@Primary标记首选bean

* @Resource

    自动装配bean实例，默认根据名称装配，这个是`JSR250的java规范注解`，不支持 @Primary 的使用

* @Inject

自动装配，`JSR330`java规范注解，使用该注解需要导入额外的inject依赖

* @Profile

    在实际开发中往往会有很多套环境 如 开发/测试/体验/线上 每个环境对应的配置都有不同，这个时候就可以使用
`@Profile`来实现多环境下的切换

    默认激活：default

    命令参数激活：Dspring.profiles.active=环境名称

# FactoryBean的使用

 Spring中有两种类型的Bean，一种是普通Bean，另一种是工厂Bean，即FactoryBean。
 工厂Bean跟普通Bean不同，其返回的对象不是指定类的一个实例，而是该工厂Bean的getObject方法所返回的对象。

**ColorFactoryBean.java**
```java
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

```
**测试**
```java
Object obj = applicationContext.getBean("colorFactoryBean");
assertEquals(Color.class, obj.getClass());
obj = applicationContext.getBean("&colorFactoryBean");
assertEquals(ColorFactoryBean.class, obj.getClass());
```

## 应用场景

FactoryBean 通常是用来创建比较复杂的bean，一般的bean 直接用xml配置即可，但如果一个bean的创建过程中涉及到很多其他的bean
和复杂的逻辑，用xml配置比较困难，这时可以考虑用FactoryBean。

## 应用案例

Mybatis3提供 mybatis-spring 中的 `org.mybatis.spring.SqlSessionFactoryBean` 通过实现FactoryBean将内部细节隐藏起来，使用者只需要
关注对外暴露的属性简化复杂逻辑下创建一个bean
```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <property name="configLocation" value="classpath:config/mybatis/mybatis-config.xml"/>
    <property name="mapperLocations" value="classpath*:com/apon/mapper/**/*.xml"/>
    <property name="typeAliasesPackage" value="com.apon.beans"/>
</bean>
```

`org.mybatis.spring.SqlSessionFactoryBean` 如下：

```java
package org.mybatis.spring;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {
private static final Log LOGGER = LogFactory.getLog(SqlSessionFactoryBean.class);
       //......
}
```

# Bean生命周期

```lua
Bean创建 -> 初始化 -> 销毁
```
可操作`初始化`、`销毁`两个周期Spring提供如下方式触发生命周期：

* 方法1：

    通过 `@Bean` 或 `<bean>` 指定 init-method/destroy-method  (初始化/销毁)

* 方法2：

    实现 `InitializingBean` 或者 `DisposableBean` (初始化/销毁)

* 方法3：

    `@PostConstruct` ：在bean创建完成并且属性赋值完成，来执行初始化方法

    `@PreDestory`：在容器销毁bean之前通知我们进行清理工作

* 方法4：

    BeanPostProcessor：bean的后置处理器；在bean初始化前后进行处理

    postProcessBeforeInitialization(...)：在初始化之前处理（如`init-method`、`InitializingBean`初始化方法之前）
    postProcessAfterInitialization(...)：初始化后的后处理

bean赋值、注入其他组件、@Autowired、生命周期注解功能、@Async等底层都是通过 BeanPostProcessor