package com.github.pengliang.annotation.conditional;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * @author pengliang  2019-03-14 18:36
 */
public class LinuxConditional implements Condition {
    private final String system = "linux";
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String systemName = !StringUtils.isEmpty(environment.getProperty("os.name")) ? environment.getProperty("os.name").toLowerCase() : "";
        if (systemName.contains(system)) {
            return true;
        }
        return false;
    }
}
