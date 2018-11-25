package spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.aop.Account;

import java.util.List;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

    @Around("execution(* spring.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n ->>> @Around method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute method
        Object result = proceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n ->>> Method duration is: " + duration / 1000.0 + " secs");

        return result;
    }

    @After("execution(* spring.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n ->>> @After (Finally) method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* spring.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n ->>> @AfterThrowing method: " + method);

        // log the exception
        // print out the results of the method call
        System.out.println("\n ->>> Exception is: " + theExc);

    }

    // add new advice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* spring.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n ->>> @AfterReturning method: " + method);

        // print out the results of the method call
        System.out.println("\n ->>> Return result: " + result);

        // post-process data...modifying it
        // convert account name to uppercase
        converAccountNameToUpperCase(result);
        System.out.println("\n ->>> Return result: " + result);
    }

    private void converAccountNameToUpperCase(List<Account> result) {

        // loop through accounts
        for (Account tempAccount : result) {
            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update account name
            tempAccount.setName(theUpperName);
        }
    }

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
