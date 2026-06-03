package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menus")

public class MenuController {
    @Autowired
    private MenuService  menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus() {

        return ResponseEntity.
                status(HttpStatus.OK).
                body(menuService.getAllMenus());
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long menuId) {

        return ResponseEntity.
                status(HttpStatus.OK).
                body(menuService.getMenuById(menuId));
    }

}
