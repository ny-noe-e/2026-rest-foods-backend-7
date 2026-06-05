package ch.noseryoung.restaurantbackend.service;

import ch.noseryoung.restaurantbackend.model.Menu;
import ch.noseryoung.restaurantbackend.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository repository;

    public List<Menu> getMenusByDietaryFilters(Boolean vegan, Boolean vegetarian, Boolean lactoseFree, Boolean glutenFree) {
        return repository.findByDietaryFilters(vegan, vegetarian, lactoseFree, glutenFree);
    }

    public Menu getMenuById(Long menuId) {
        return repository.findById(menuId).orElseThrow(() -> new EntityNotFoundException("Menu with id: " + menuId + " not found"));
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
