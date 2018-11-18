package spring.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public boolean addMember() {
        System.out.println(getClass() + ": adding a Membership account");
        return true;
    }

    public void goToSleep() {
        System.out.println(getClass() + ":  goToSleep()");
    }
}
