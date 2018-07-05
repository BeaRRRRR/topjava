package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.dao.impl.MealDaoImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MealServlet extends HttpServlet {
    private static final Logger Log = LoggerFactory.getLogger(MealServlet.class);
    MealDaoImpl mealDao = MealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Log.debug("redirect to meals");
        req.setAttribute("meals", MealsUtil.getFilteredWithExceededInOnePass2(new ArrayList<>(mealDao.getMealMap().values()), LocalTime.MIN,LocalTime.MAX,2000));
        req.setAttribute("formatter",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
