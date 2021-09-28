package com.nh.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) //@LogExecutionTime 어노테이션은 메소드에 사용할 것이다.
@Retention(RetentionPolicy.RUNTIME) //RUNTIME 까지 유지할 것이다.
public @interface LogExecutionTime {
}
