package spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.service.TrafficFortuneService;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

    private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMethod main AroundDemoApp");
        logger.info("\nCalling getFortune()");
        String data = fortuneService.getFortune();

        logger.info("\nFortune is: " + data);
        logger.info("\nFinished");

        // close the context
        context.close();
    }
}
