package ch.noseryoung.restaurantbackend.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Menu name cannot be blank")
    @Size(max = 100, message = "Menu name must be under 100 characters")
    @Column(name = "menu_name", nullable = false, length = 100)
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(length = 500)
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.05", inclusive = true, message = "Price must be at least 0.05")
    @Digits(integer = 6, fraction = 2, message = "Price must have at most 2 decimal places")
    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal price;

    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must be under 50 characters")
    @Column(nullable = false, length = 50)
    private String category;
    private boolean chefChoice;

    private boolean glutenFree;
    private boolean lactoseFree;
    private boolean containsNuts;
    private boolean vegan;
    private boolean vegetarian;

    @JsonIgnore
    @AssertTrue(message = "Price must be in 0.05 steps")
    public boolean isPriceInFiveCentSteps() {
        if (price == null) {
            return true;
        }
        try {
            BigDecimal scaled = price.setScale(2, RoundingMode.UNNECESSARY);
            return scaled.movePointRight(2)
                    .remainder(new BigDecimal("5"))
                    .compareTo(BigDecimal.ZERO) == 0;
        } catch (ArithmeticException ex) {
            return false;
        }
    }

}
