package spring.aop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addMember() {
        System.out.println(getClass() + ": adding a Membership account");
    }
}
