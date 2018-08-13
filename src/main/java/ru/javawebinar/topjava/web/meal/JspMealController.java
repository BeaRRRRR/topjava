package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    @Autowired
    private MealRestController mealRestController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String getUpdateForm(Model model, @RequestParam(value = "id") String id) {
        model.addAttribute("meal", mealRestController.get(Integer.parseInt("id")));
        return "mealForm";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(Model model,
                      @RequestParam(value = "id") Integer id,
                      @RequestParam(value = "dateTime") String dateTime,
                      @RequestParam(value = "descpription") String description,
                      @RequestParam(value = "calories") Integer calories) {

        Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                calories
        );
        mealRestController.update(meal, id);
        return "redirect:/meals";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String getAddForm() {
        return "create";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String add(Model model,
                      @RequestParam(value = "dateTime") String dateTime,
                      @RequestParam(value = "descpription") String description,
                      @RequestParam(value = "calories") Integer calories) {
        Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                calories
        );
        mealRestController.create(meal);
        return "redirect:/meals";
    }

    @RequestMapping(value = "filter", method = RequestMethod.GET)
    public String filter(Model model,
                         @RequestParam(value = "startDate") String startDateParam,
                         @RequestParam(value = "endDate") String endDateParam,
                         @RequestParam(value = "startTime") String startTimeParam,
                         @RequestParam(value = "endTime") String endTimeParam) {

        model.addAttribute("meals",mealRestController.getBetween(LocalDate.parse(startDateParam), LocalTime.parse(startTimeParam),LocalDate.parse(endDateParam),LocalTime.parse(endTimeParam)));
        return "redirect:/meals";

    }


}
