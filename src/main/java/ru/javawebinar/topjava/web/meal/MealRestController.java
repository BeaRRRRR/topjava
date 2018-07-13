package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<MealWithExceed>getAll(LocalTime startTime,LocalTime endTIme){
        log.info("getAll {}",startTime,endTIme);
        return MealsUtil.getFilteredWithExceeded(service.getAll(SecurityUtil.authUserId()),MealsUtil.DEFAULT_CALORIES_PER_DAY,startTime,endTIme);
    }

    public List<MealWithExceed>getAll(){
        log.info("getAll without params");
        return MealsUtil.getFilteredWithExceeded(service.getAll(SecurityUtil.authUserId()),MealsUtil.DEFAULT_CALORIES_PER_DAY,LocalTime.MIN,LocalTime.MAX);
    }

    public Meal get(int id){
        log.info("get {}",id);
        return service.get(id,SecurityUtil.authUserId());
    }

    public Meal update(int id){
        log.info("update {}",id);
        return service.update(service.get(id,SecurityUtil.authUserId()),SecurityUtil.authUserId());
    }

    public void delete(int id){
        log.info("delete {}",id);
        service.delete(id,SecurityUtil.authUserId());

    }

}