package com.github.pengliang.annotation.myImport;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author pengliang  2019-03-15 21:49
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 返回需要注入全类名bean
     * @param importingClassMetadata 当前类注解信息
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.github.pengliang.annotation.bean.Read"};
    }
}
