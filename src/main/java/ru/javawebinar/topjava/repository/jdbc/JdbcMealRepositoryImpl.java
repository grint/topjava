package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {

    @Override
    public Meal save(Meal Meal) {
        return null;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Meal get(int id) {
        return null;
    }

    @Override
    public Meal get(int id, int userId) {
        return null;
    }

    @Override
    public Collection<Meal> getAll() {
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return null;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return null;
    }
}
