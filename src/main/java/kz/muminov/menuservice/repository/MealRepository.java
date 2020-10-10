package kz.muminov.menuservice.repository;

import kz.muminov.menuservice.model.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    @Query(value = "select * from meal where id not in (select meal_id from menu_meals)", nativeQuery = true)
    List<Meal> getMealsWithoutMenu();

}
