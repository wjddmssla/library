package com.korit.library.aop;

import com.korit.library.exception.CustomValidationException;
import com.korit.library.web.dto.UserDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {

    @Pointcut("@annotation(com.korit.library.aop.annotation.ValidAspect)")
    private void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
            }else if(arg.getClass() == UserDto.class) {
                System.out.println(arg);
            }
        }

        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            throw new CustomValidationException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }

}