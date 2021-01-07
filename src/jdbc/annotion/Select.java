package jdbc.annotion;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited//注解可继承
public @interface Select {
    public String value();
}
