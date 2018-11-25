package spring.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.aop.service.TrafficFortuneService;

public class AroundDemoApp {

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from the spring container
        TrafficFortuneService fortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMethod main AroundDemoApp");
        System.out.println("\nCalling getFortune()");
        String data = fortuneService.getFortune();

        System.out.println("\nFortune is: " + data);
        System.out.println("\nFinished");

        // close the context
        context.close();
    }
}
