package spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.aop.Account;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

    /* @Before advice
    call this method for any execution method that has a signature below (point cut expression) */
    @Before("spring.aop.aspects.AopExpressions.forDaoPackage()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n ->>> Executing @Before advice on the method addAccount()");

        // display method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method Signature: " + methodSig);

        // display method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account) {
                // downcast and print Account stuff
                Account account = (Account) tempArg;
                System.out.println("Account name:" + account.getName());
                System.out.println("Account level:" + account.getLevel());
            }
        }
    }
}
