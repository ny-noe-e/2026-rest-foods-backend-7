package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.service.MenuService;

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
}
