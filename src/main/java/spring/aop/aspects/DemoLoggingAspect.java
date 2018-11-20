package spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {

    // this is where we add all of related advices for logging

    @Pointcut("execution(* spring.aop.dao.*.*(..))")
    private void forDaoPackage() {
    }

    /* @Before advice
    call this method for any execution method that has a signature below (point cut expression) */
    @Before("forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ->>> Executing @Before advice on the method addAccount()");
    }

    @Before("forDaoPackage()")
    public void performApiAnalytics() {
        System.out.println("\n ->>> Performing API analytics");
    }
}
