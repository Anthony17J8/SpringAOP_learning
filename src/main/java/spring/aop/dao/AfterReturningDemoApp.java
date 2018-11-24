package spring.aop.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.Account;
import spring.aop.DemoConfig;

import java.util.List;

public class AfterReturningDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = accountDAO.findAccounts();

        // display the accounts
        System.out.println("\nMethod main AfterReturningDemoApp: ");
        System.out.println("====");
        System.out.println(theAccounts);

        // close the context
        context.close();
    }
}
