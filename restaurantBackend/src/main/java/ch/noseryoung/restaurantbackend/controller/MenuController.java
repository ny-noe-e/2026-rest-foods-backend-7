package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenus(
            @RequestParam(required = false) Boolean vegan,
            @RequestParam(required = false) Boolean vegetarian,
            @RequestParam(required = false) Boolean lactoseFree,
            @RequestParam(required = false) Boolean glutenFree) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenusByDietaryFilters(vegan, vegetarian, lactoseFree, glutenFree));
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Menu> getMenuById(@PathVariable Long menuId) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.getMenuById(menuId));
    }

    @PostMapping
    public ResponseEntity<Menu> createMenu(@Valid @RequestBody Menu menu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.createMenu(menu));
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId, @Valid @RequestBody Menu menu) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.updateMenu(menuId, menu));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
