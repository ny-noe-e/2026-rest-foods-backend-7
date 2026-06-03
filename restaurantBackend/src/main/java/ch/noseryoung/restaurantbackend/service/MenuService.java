package ch.noseryoung.restaurantbackend.service;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public  Menu getMenuById(Long menuId) {
        return repository.findById(menuId)
                .orElseThrow(() -> new EntityNotFoundException("Menu with id: " + menuId + " not found"));
    }

    public Menu createMenu(Menu menu) {
        return repository.save(menu);
    }

    public Menu updateMenu(Long menuId, Menu menuDetails) {
        if (repository.existsById(menuId)) {
            menuDetails.setId(menuId);

            return repository.save(menuDetails);
        } else {
            throw new EntityNotFoundException("Menu with id: " + menuId + " not found");
        }
    }

    public void deleteMenu(Long menuId) {
        if (repository.existsById(menuId)) {
            repository.deleteById(menuId);
        } else {
            throw new EntityNotFoundException("Menu with id: " + menuId + " not found");
        }
    }


}
