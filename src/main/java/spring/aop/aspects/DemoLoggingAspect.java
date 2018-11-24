package spring.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import spring.aop.Account;

import java.util.List;

@Aspect
@Component
@Order(2)
public class DemoLoggingAspect {

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
