package ch.noseryoung.restaurantbackend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category;
    private boolean chefChoice;

    private boolean glutenFree;
    private boolean lactoseFree;
    private boolean containsNuts;
    private boolean vegan;
    private boolean vegetarian;

}
