package spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {

    // this is where we add all of related advices for logging

     /* @Before advice
     call this method for any execution method that has a signature below (point cut expression) */
    @Before("execution(public void add*(spring.aop.Account))")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ->>> Executing @Before advice on the method addAccount()");
    }

}
