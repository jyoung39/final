package springxml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import springxml.beans.Item;
import springxml.services.LogService;

public class SpringApp {
    public static void main(String[] args) {
        // load the Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from Spring container
        //CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);
        Item theItem = context.getBean("myCheesePizza", Item.class);


        // call methods on the bean
        //System.out.println(theCoach.getDailyWorkout());


        // call getters for literal values
        //System.out.println(theCoach.getEmailAddress());
        System.out.println(theItem.getId());
        System.out.println(theItem.getItemName());
        System.out.println(theItem.getItemPrice());
        System.out.println(theItem.getItemDescription());
        System.out.println(theItem.getImagePath());
        theItem.setLog("Test");
        theItem.displayMessage();


        // close the context
        context.close();
    }
}
