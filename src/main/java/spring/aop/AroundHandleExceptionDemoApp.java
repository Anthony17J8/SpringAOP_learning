package spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.service.TrafficFortuneService;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

    private static Logger logger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMethod main AroundHandlerExceptionDemoApp");
        logger.info("\nCalling getFortune()");

        boolean tripWire = true;
        String data = fortuneService.getFortune(tripWire);

        logger.info("\nFortune is: " + data);
        logger.info("\nFinished");

        // close the context
        context.close();
    }
}
