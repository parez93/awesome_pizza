package com.parezzan.awesome_pizza.aspects.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.parezzan.awesome_pizza.aspects.log.Loggable)")
    public void loggableMethods() {}

    @Before("loggableMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArguments = joinPoint.getArgs();

        StringBuilder message = new StringBuilder("Method " + methodName + " called with parameters: ");

        for (Object arg : methodArguments) {
            message.append(arg).append(", ");
        }

        // Remove last coma and space in args
        if (methodArguments.length > 0) {
            message.setLength(message.length() - 2);
        }

        logger.info(message.toString());
    }

    @After("loggableMethods()")
    public void logMethodExit() {
        logger.info("Method execution finished");
    }

    @AfterReturning("loggableMethods()")
    public void logMethodReturn() {
        logger.info("Method returned successfully");
    }

    @AfterThrowing("loggableMethods()")
    public void logMethodException() {
        logger.error("Exception occurred during method execution");
    }
}
