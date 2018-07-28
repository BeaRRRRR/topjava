package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.Util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.getUser().getId() == userId) {
            User ref = entityManager.getReference(User.class, userId);
            meal.setUser(ref);
            return meal;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return entityManager.createQuery("delete from Meal m where m.id=:id AND m.user.id=:userId")
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = entityManager.find(Meal.class, id);
        return meal.getId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {

        return entityManager.createQuery("SELECT m from Meal m where m.user.id=:userId",Meal.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = entityManager.createQuery("SELECT m from Meal m left join m.user where m.user.id=:userId", Meal.class)
                .setParameter("userId", userId)
                .getResultList();
        meals.removeIf(e -> !Util.isBetween(e.getDateTime(),startDate,endDate));
        return meals;
    }
}