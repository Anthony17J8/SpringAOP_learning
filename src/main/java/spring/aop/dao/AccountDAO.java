package spring.aop.dao;

import org.springframework.stereotype.Component;
import spring.aop.Account;

@Component
public class AccountDAO {

    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + " doing DB work: adding an account");
    }

    public boolean doWork() {
        System.out.println(getClass() + " doWork()");
        return false;
    }
}
