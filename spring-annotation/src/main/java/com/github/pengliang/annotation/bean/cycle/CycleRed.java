package com.github.pengliang.annotation.bean.cycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author pengliang  2019-03-19 16:30
 */
@Component
public class CycleRed implements InitializingBean,DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CycleRed->Initializable->afterPropertiesSet");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("CycleRed->DisposableBean->destroy");
    }


}
