package ru.javawebinar.topjava.dao.impl;

import ru.javawebinar.topjava.dao.interfaces.MealDao;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static MealDaoImpl ourInstance = new MealDaoImpl();

    public static MealDaoImpl getInstance() {
        return ourInstance;
    }

    private MealDaoImpl() {
    }

    private ConcurrentHashMap<AtomicInteger,Meal> mealMap = new ConcurrentHashMap<>();
    {
        mealMap.put(new AtomicInteger(0),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        mealMap.put(new AtomicInteger(1),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        mealMap.put(new AtomicInteger(2),new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        mealMap.put(new AtomicInteger(3),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        mealMap.put(new AtomicInteger(4),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        mealMap.put(new AtomicInteger(5),new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    @Override
    public Meal getMeal(int id) {
        return mealMap.get(new AtomicInteger(id));
    }

    @Override
    public Meal deleteMeal(int id) {
        return mealMap.remove(new AtomicInteger(id));
    }

    public ConcurrentHashMap<AtomicInteger, Meal> getMealMap() {
        return mealMap;
    }
}
