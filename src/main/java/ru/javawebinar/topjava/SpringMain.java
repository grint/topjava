package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

            adminUserController.getAll();
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 29, 8, 0), "Breakfast", 50));
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 29, 12, 0), "Lunch", 100));
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 29, 16, 0), "Dinner", 150));
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 30, 8, 0), "Breakfast", 500));
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 30, 12, 0), "Lunch", 1000));
            mealRestController.save(new Meal(LocalDateTime.of(2017, Month.APRIL, 30, 16, 0), "Dinner", 1500));
            mealRestController.getAll();
        }
    }
}
