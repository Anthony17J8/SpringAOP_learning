package spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.aop.Account;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

    private Logger logger = Logger.getLogger(DemoLoggingAspect.class.getName());

    @Around("execution(* spring.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n ->>> @Around method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute method
        Object result = proceedingJoinPoint.proceed();

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        logger.info("\n ->>> Method duration is: " + duration / 1000.0 + " secs");

        return result;
    }

    @After("execution(* spring.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n ->>> @After (Finally) method: " + method);
    }

    @AfterThrowing(pointcut = "execution(* spring.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable theExc) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n ->>> @AfterThrowing method: " + method);

        // log the exception
        // print out the results of the method call
        logger.info("\n ->>> Exception is: " + theExc);

    }

    // add new advice for @AfterReturning on the findAccounts method
    @AfterReturning(pointcut = "execution(* spring.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n ->>> @AfterReturning method: " + method);

        // print out the results of the method call
        logger.info("\n ->>> Return result: " + result);

        // post-process data...modifying it
        // convert account name to uppercase
        converAccountNameToUpperCase(result);
        logger.info("\n ->>> Return result: " + result);
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
        logger.info("\n ->>> Executing @Before advice on the method addAccount()");

        // display method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        logger.info("Method Signature: " + methodSig);

        // display method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info(tempArg.toString());

            if (tempArg instanceof Account) {
                // downcast and print Account stuff
                Account account = (Account) tempArg;
                logger.info("Account name:" + account.getName());
                logger.info("Account level:" + account.getLevel());
            }
        }
    }
}
