package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

public class MealAjaxController extends AbstractMealController{

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam(value = "id")Integer id,
                               @RequestParam(value = "dateTime") LocalDateTime dateTime,
                               @RequestParam(value = "description")String description,
                               @RequestParam(value = "calories")Integer calories){
        Meal meal = new Meal(id,dateTime,description,calories);
        if(meal.isNew()){
            super.create(meal);
        }

    }

}
