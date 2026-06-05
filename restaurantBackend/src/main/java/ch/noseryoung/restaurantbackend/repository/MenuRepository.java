package ch.noseryoung.restaurantbackend.repository;

import ch.noseryoung.restaurantbackend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("""
            select m
            from Menu m
            where (:vegan is null or m.vegan = :vegan)
            	and (:vegetarian is null or m.vegetarian = :vegetarian)
            	and (:lactoseFree is null or m.lactoseFree = :lactoseFree)
            	and (:glutenFree is null or m.glutenFree = :glutenFree)
            """)
    List<Menu> findByDietaryFilters(@Param("vegan") Boolean vegan, @Param("vegetarian") Boolean vegetarian, @Param("lactoseFree") Boolean lactoseFree, @Param("glutenFree") Boolean glutenFree);
}
