package top.panson.irpc.framework.core.common.annotations;

import java.lang.annotation.*;

/**
 * @Author linhao
 * @Date created in 11:42 上午 2022/3/6
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SPI {

    String value() default "";
}
