package kz.muminov.menuservice.controller;

import kz.muminov.menuservice.model.entity.Meal;
import kz.muminov.menuservice.repository.MealRepository;
import kz.muminov.menuservice.service.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MealController {

    private static final String MEAL = "/meal";
    private static final String GET_MEALS = MEAL + "/list";
    private static final String GET_MEAL = MEAL + "/{id}";
    private static final String DELETE_MEAL = MEAL + "/{id}";

    private final MealService mealService;
    private final MealRepository mealRepository;

    @PostMapping(MEAL)
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal){
        return new ResponseEntity<>(mealService.addMeal(meal), HttpStatus.CREATED);
    }

    @GetMapping(GET_MEAL)
    public ResponseEntity<Meal> getMeal(@PathVariable Long id){
        return new ResponseEntity<>(mealService.getMeal(id), HttpStatus.OK);
    }

    @GetMapping(GET_MEALS)
    public ResponseEntity<List<Meal>> getMeals(){
        return new ResponseEntity<>(mealRepository.findAll(), HttpStatus.OK);
    }

    @DeleteMapping(DELETE_MEAL)
    public ResponseEntity<String> deleteMeal(@PathVariable Long id){
        return new ResponseEntity<>(mealService.deleteMeal(id), HttpStatus.OK);
    }

}



