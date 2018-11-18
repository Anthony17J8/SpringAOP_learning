package spring.aop.dao;

import org.springframework.stereotype.Component;
import spring.aop.Account;

@Component
public class AccountDAO {

    public void addAccount(Account theAccount) {
        System.out.println(getClass() + " doing DB work: adding an account");
    }
}
