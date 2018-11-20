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

    // creating pointcut for getter methods
    @Pointcut("execution(* spring.aop.dao.*.get*(..))")
    private void getter() {
    }

    // creating pointcut for setter methods
    @Pointcut("execution(* spring.aop.dao.*.set*(..))")
    private void setter() {
    }

    // creating pointcut: include package ... exclude getter/setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackageNoGetterSetter() {
    }

    /* @Before advice
    call this method for any execution method that has a signature below (point cut expression) */
    @Before("forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice() {
        System.out.println("\n ->>> Executing @Before advice on the method addAccount()");
    }

    @Before("forDaoPackageNoGetterSetter()")
    public void performApiAnalytics() {
        System.out.println("\n ->>> Performing API analytics");
    }
}
