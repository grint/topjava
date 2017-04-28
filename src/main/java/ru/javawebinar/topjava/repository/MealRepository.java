package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;
import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    Meal save(Meal Meal);
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    void delete(int id);
    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    Meal get(int id);
    // null if meal do not belong to userId
    Meal get(int id, int userId);

    Collection<Meal> getAll();
    // ORDERED dateTime
    List<Meal> getAll(int userId);

    // ORDERED dateTime
    List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
