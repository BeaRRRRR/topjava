package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/meals")
public class JspMealController {

    @Autowired
    private MealRestController mealRestController;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getAll(Model model){
        model.addAttribute("meals",mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public String getAddFrom(){
        return "create";
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String add(){

    }

}
