package ch.noseryoung.restaurantbackend.service;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository repository;

    public List<Menu> getAllMenus() {
        return repository.findAll();
    }
}
