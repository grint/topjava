package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDaoInMemory implements MealDao {
    private static final Logger LOG = getLogger(MealDaoInMemory.class);

    private AtomicInteger counter = new AtomicInteger(0);
    private final List<Meal> meals = Collections.synchronizedList(new ArrayList<>());

    public MealDaoInMemory() {
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.add(new Meal(getId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Integer getId(){
        return counter.incrementAndGet();
    }

    @Override
    public void add(Meal meal) {
        meals.add(meal);
    }

    @Override
    public void update(Integer id, Meal meal) {
        synchronized (meals) {
            meals.stream().filter(mealItem -> mealItem.getId() == id).forEach(mealItem -> {
                meals.set(meals.indexOf(mealItem), meal);
                LOG.debug("id:" + id + " " + meal.toString());
            });
        }
    }

    @Override
    public void delete(Integer id) {
        synchronized (meals) {
            meals.removeIf(meal -> meal.getId() == id);
        }
    }

    @Override
    public List<MealWithExceed> getAll() {
        return MealsUtil.getMealsWithExceed(meals);
    }

    @Override
    public Meal get(Integer id) {
        synchronized (meals) {
            for (Meal meal : meals) {
                if (meal.getId() == id) {
                    return meal;
                }
            }
        }
        return null;
    }
}