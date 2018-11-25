package spring.aop.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TrafficFortuneService {

    public String getFortune(){

        // simulate a delay
        try {
            System.out.println("Sleeping...");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }

        // return a fortune
        return "Expect heavy traffic this morning";
    }
}
