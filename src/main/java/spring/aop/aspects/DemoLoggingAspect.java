package spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

    /* @Before advice
    call this method for any execution method that has a signature below (point cut expression) */
    @Before("spring.aop.aspects.AopExpressions.forDaoPackage()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ->>> Executing @Before advice on the method addAccount()");
    }
}
