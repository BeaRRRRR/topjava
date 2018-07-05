package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.impl.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class EditServlet extends HttpServlet {
    MealDaoImpl mealDao = MealDaoImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id",Integer.parseInt(req.getParameter("id")));
        req.getRequestDispatcher("edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(String.valueOf(req.getAttribute("id")));
        LocalDateTime localDateTime = (LocalDateTime) req.getAttribute("date");
        String description = (String) req.getAttribute("desc");
        int calorie = (int) req.getAttribute("calories");
        Meal meal = mealDao.getMeal(id);
    }
}
