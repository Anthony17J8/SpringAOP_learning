package spring.aop.dao;

import org.springframework.stereotype.Component;
import spring.aop.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    // add a new method: findAccounts()
    public List<Account> findAccounts() {
        List<Account> accounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("Jason", "Junior");
        Account temp2 = new Account("Mary", "Middle");
        Account temp3 = new Account("Anthony", "Senior");

        // add them to list
        accounts.add(temp1);
        accounts.add(temp2);
        accounts.add(temp3);

        return accounts;
    }

    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + " doing DB work: adding an account");
    }

    public boolean doWork() {
        System.out.println(getClass() + " doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + " in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
