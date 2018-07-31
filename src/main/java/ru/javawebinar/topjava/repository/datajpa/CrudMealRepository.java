package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Modifying
    @Query(value = Meal.GET_BETWEEN)
    List<Meal> findAllBetweenByUserId(@Param(value = "user_id") int userId,
                                      @Param(value = "startDate")LocalDateTime startDate,
                                      @Param(value = "endDate") LocalDateTime endDate);

    @Modifying
    @Query(value = "select m from Meal m where m.id=:id and m.user.id=:user_id")
    List<Meal> findAllByUserId(@Param(value = "user_id") int userId,Sort sort);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Optional<Meal> findById(Integer integer);

    @Modifying
    @Query(value = "select m from Meal m where m.id=:id and m.user.id=:user_id")
    Meal findByIdAndUserId(@Param(value = "id") int id,
                           @Param(value = "user_id") int userId);

    @Transactional
    @Modifying
    @Query(value = "delete from Meal m where m.id=:id")
    int deleteById(@Param("id") int id);
}
