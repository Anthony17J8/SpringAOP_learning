package spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.dao.AccountDAO;

import java.util.List;

public class AfterThrowingDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // call method to find the accounts
        List<Account> theAccounts = null;
        try {
            // add a boolean flag to simulate exceptions
            boolean tripWire = true;
            accountDAO.findAccounts(tripWire);
        } catch (Exception exc) {
            System.out.println("\nMethod main: caught exception: " + exc);
        }

        // display the accounts
        System.out.println("\nMethod main AfterThrowingDemoApp: ");
        System.out.println("====");
        System.out.println(theAccounts);

        // close the context
        context.close();
    }
}
