package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        repository.delete(id,userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id, userId);
        if(meal ==null) throw new NotFoundException("еда не принадлежит данному пользователю");
        return meal;
    }

    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException{
        Meal save = repository.save(meal);
        if(save ==null) throw new NotFoundException("еда не принадлежит данному пользователю");
        return save;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Collection<Meal> meals = repository.getAll(userId);
        return meals;
    }

}