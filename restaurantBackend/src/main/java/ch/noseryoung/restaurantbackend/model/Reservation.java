package ch.noseryoung.restaurantbackend.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Customer name is required")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String customerName;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number cannot exceed 20 characters")
    @Column(nullable = false, length = 20)
    private String phoneNumber;

    @NotNull(message = "Number of persons is required")
    @Max(value = 20, message = "Reservation must be for max 20 people")
    @Column(nullable = false)
    private Integer numberOfPersons;

    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalDateTime reservedFrom;

    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalDateTime reservedTo;

    @NotNull(message = "Table identifier is required")
    @Column(nullable = false)
    private String tableId;
}
