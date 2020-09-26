package kz.muminov.menuservice.service;

import kz.muminov.menuservice.model.entity.Meal;
import kz.muminov.menuservice.repository.MealRepository;
import kz.muminov.menuservice.util.ExceptionUtils;
import kz.muminov.menuservice.util.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final ExceptionUtils exceptionUtils;

    public Meal addMeal(Meal meal){
        return mealRepository.save(meal);
    }

    public String deleteMeal(Long id){

        if (!mealRepository.existsById(id)){
            exceptionUtils.throwDefaultException(MessageCode.MEAL_DOES_NOT_EXIST);
        }

        mealRepository.deleteById(id);

        return "Meal was successfully deleted";

    }

    public Meal getMeal(Long id){

        if (!mealRepository.existsById(id)){
            exceptionUtils.throwDefaultException(MessageCode.MEAL_DOES_NOT_EXIST);
        }

        return mealRepository.findById(id).get();

    }



}
