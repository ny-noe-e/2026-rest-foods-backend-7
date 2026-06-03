package ch.noseryoung.restaurantbackend.repository;
import ch.noseryoung.restaurantbackend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long>  {
}
