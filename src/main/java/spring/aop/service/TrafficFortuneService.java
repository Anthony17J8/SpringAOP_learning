package spring.aop.service;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Component
public class TrafficFortuneService {

    private Logger logger = Logger.getLogger(TrafficFortuneService.class.getName());

    public String getFortune(){

        // simulate a delay
        try {
            logger.info("Sleeping...");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }

        // return a fortune
        return "Expect heavy traffic this morning";
    }

    public String getFortune(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Major problem!");
        }
        return getFortune();
    }
}
