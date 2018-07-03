package ru.javawebinar.topjava.dao.interfaces;

import ru.javawebinar.topjava.model.Meal;

public interface MealDao {

    Meal getMeal(int id);
    Meal deleteMeal(int id);

}
