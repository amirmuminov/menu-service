package kz.muminov.menuservice.controller;

import kz.muminov.menuservice.model.dto.MealsListDTO;
import kz.muminov.menuservice.model.dto.MenuDTO;
import kz.muminov.menuservice.model.entity.Menu;
import kz.muminov.menuservice.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class MenuController {

    private final MenuService menuService;

    private static final String MENU = "/menu";
    private static final String GET_MENU = MENU + "/{id}";
    private static final String GET_MENUS = MENU + "/list";
    private static final String ADD_MEALS = MENU + "/{id}";

    @GetMapping(GET_MENU)
    public ResponseEntity<Menu> getMenu(@PathVariable Long id){
        return new ResponseEntity<>(menuService.getMenu(id), HttpStatus.OK);
    }

    @GetMapping(GET_MENUS)
    public ResponseEntity<List<Menu>> getMenus(){
        return new ResponseEntity<>(menuService.getMenus(), HttpStatus.OK);
    }

    @PostMapping(MENU)
    public ResponseEntity<Menu> createMenu(@RequestBody MenuDTO menuDTO){
        return new ResponseEntity<>(menuService.addMenu(menuDTO), HttpStatus.OK);
    }

    @PutMapping(ADD_MEALS)
    public ResponseEntity<String> addMealsToMenu(@RequestBody MealsListDTO mealsListDTO,
                                                 @PathVariable Long id){
        return new ResponseEntity<>(menuService.addMealsToMenu(mealsListDTO, id), HttpStatus.OK);
    }

}
