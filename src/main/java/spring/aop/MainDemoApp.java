package spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.dao.AccountDAO;
import spring.aop.dao.MembershipDAO;

public class MainDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        // get the membership bean from the spring container
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        // call a business method
        Account theAccount = new Account();
        accountDAO.addAccount(theAccount, true);
        accountDAO.doWork();

        // call the Membership business method
        membershipDAO.addMember();
        membershipDAO.goToSleep();

        // call accountDAO getter/setters
        accountDAO.setName("Billy");
        accountDAO.setServiceCode("gold");

        accountDAO.getName();
        accountDAO.getServiceCode();

        // close the context
        context.close();
    }
}
