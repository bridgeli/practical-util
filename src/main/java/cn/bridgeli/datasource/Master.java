package cn.bridgeli.datasource;

import java.lang.annotation.*;

/**
 * 主库配置注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Master {
    
}
