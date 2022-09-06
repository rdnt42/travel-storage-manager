package com.summerdev.travelstoragemanager.aspect;

import com.summerdev.travelstoragemanager.entity.InfoTask;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: marowak
 * Date: 06.09.2022
 * Time: 22:48
 */
@ExtendWith(MockitoExtension.class)
class LogUpdateCountAspectTest {

    private final LogUpdateCountAspect logUpdateCountAspect = new LogUpdateCountAspect();

    @Test
    void logUpdateCountSuccess() {
        JoinPoint joinPoint = mock(JoinPoint.class);
        Signature signature = mock(Signature.class);
        when(joinPoint.getArgs())
                .thenReturn(getParams());
        when(joinPoint.getSignature())
                .thenReturn(signature);

        logUpdateCountAspect.logUpdateCount(joinPoint, 0);

        verify(joinPoint, times(1))
                .getArgs();
        verify(joinPoint, times(1))
                .getSignature();
    }

    private Object[] getParams() {
        return new Object[]{"Param1", new InfoTask()};
    }
}