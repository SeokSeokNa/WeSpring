package com.nh.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Around("execution(* com.nh..*Controller.*(..))"
            +" || execution(* com.nh..*service..*Impl.*(..))"
    )
    public Object logPrint(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();

        String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        String name = "";

        if (type.contains("Controller")) {
            name = "Controller : ";
        } else if (type.contains("Service")) {
            name = "Service : ";
        }

        long end = System.currentTimeMillis();

        logger.info(name + type + "."+proceedingJoinPoint.getSignature().getName()+"()");
        logger.info("Argument/Parameter : " + Arrays.toString(proceedingJoinPoint.getArgs()));
        logger.info("Running Time : " + (end -start));
        logger.info("-----------------------------------------------------------------------------------");

        return result;
    }


}
