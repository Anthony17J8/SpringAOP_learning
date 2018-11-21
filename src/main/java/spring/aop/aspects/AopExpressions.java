package spring.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    // this is where we add all of related advices for logging

    @Pointcut("execution(* spring.aop.dao.*.*(..))")
    public void forDaoPackage() {
    }

    // creating pointcut for getter methods
    @Pointcut("execution(* spring.aop.dao.*.get*(..))")
    public void getter() {
    }

    // creating pointcut for setter methods
    @Pointcut("execution(* spring.aop.dao.*.set*(..))")
    public void setter() {
    }

    // creating pointcut: include package ... exclude getter/setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {
    }
}
