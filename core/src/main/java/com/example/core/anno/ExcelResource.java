package com.example.core.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface ExcelResource {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";
}
