package seu.cc.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuxiangyu on 2017/7/3.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface GetMsg {
    int id();
    String name() default "default";
}