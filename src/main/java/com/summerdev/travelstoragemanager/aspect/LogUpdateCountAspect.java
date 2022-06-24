package com.summerdev.travelstoragemanager.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 22.06.2022
 * Time: 23:43
 */
@Slf4j
@Aspect
@Component
public class LogUpdateCountAspect {

    @AfterReturning(pointcut = "@annotation(LogUpdateCount)", returning = "retVal")
    public void logUpdateCount(JoinPoint joinPoint, Object retVal){
        Object[] args = joinPoint.getArgs();
        if (args.length != 3) {
            return;
        }

        String name = joinPoint.getSignature().toShortString();
        log.debug("Updater: {}, updated count: {}, task id: {}, cursor: {}", name, retVal, args[2], args[1]);
    }
}
