package kz.muminov.menuservice.service;

import kz.muminov.menuservice.model.dto.MealsListDTO;
import kz.muminov.menuservice.model.dto.MenuDTO;
import kz.muminov.menuservice.model.entity.Meal;
import kz.muminov.menuservice.model.entity.Menu;
import kz.muminov.menuservice.repository.MealRepository;
import kz.muminov.menuservice.repository.MenuRepository;
import kz.muminov.menuservice.util.ExceptionUtils;
import kz.muminov.menuservice.util.MessageCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final MealRepository mealRepository;
    private final ExceptionUtils exceptionUtils;

    public Menu addMenu(MenuDTO menuDTO){
        Menu menu = new Menu();

        menu.setName(menuDTO.getName());
        menu.setDescription(menuDTO.getDescription());

        return menuRepository.save(menu);

    }

    public String addMealsToMenu(MealsListDTO mealsListDTO, Long menuId){

        List<Meal> existingMeals = new ArrayList<>();

        if(!menuRepository.existsById(menuId)){
            exceptionUtils.throwDefaultException(MessageCode.MENU_DOES_NOT_EXIST);
        }

        Menu menu = menuRepository.findById(menuId).get();

        for(Meal meal: mealsListDTO.getMeals()){

            if(!mealRepository.existsById(meal.getId())){
                exceptionUtils.throwDefaultException(MessageCode.MEAL_DOES_NOT_EXIST);
            }

            menu.getMeals().add(mealRepository.findById(meal.getId()).get());

        }

        menuRepository.save(menu);

        return "Meals were successfully added to the menu";

    }

    public Menu getMenu(Long id){

        if(!menuRepository.existsById(id)){
            exceptionUtils.throwDefaultException(MessageCode.MENU_DOES_NOT_EXIST);
        }

        return menuRepository.findById(id).get();

    }

    public List<Menu> getMenus(){
        return menuRepository.findAll();
    }

}
