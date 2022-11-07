package com.example.demo.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

//    @Pointcut("within(com.example.demo.controllers..*) || " +
//            "within(com.example.demo..*)") //where we use this Logger
    @Pointcut("within(com.example.demo.controllers.EmployeeController)")
    public void definePackagePointcuts(){
        // this empty method help me to define the location specified in the pointcut
    }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint pjp){
        log.debug(" \n \n \n ");
        log.debug("********* BEFORE METHOD EXECUTION ********* \n" +
                " {}.{} () with arguments[s] = {}",
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(),
                Arrays.toString(pjp.getArgs()));
        log.debug("__________________________________________________ \n \n \n");

        Object o = null;
        try {
            o = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        log.debug(" \n \n \n ");
        log.debug("********* AFTER METHOD EXECUTION ********* \n" +
                        " {}.{} () with arguments[s] = {}",
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(),
                Arrays.toString(pjp.getArgs()));
        log.debug("__________________________________________________ \n \n \n");

        return o;
    }
}
