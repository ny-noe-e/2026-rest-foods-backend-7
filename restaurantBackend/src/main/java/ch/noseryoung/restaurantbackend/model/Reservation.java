package ch.noseryoung.restaurantbackend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
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
    @Pattern(regexp = "^\\+41\\d{9}$", message = "Phone number must be in +41XXXXXXXXX format")
    @Column(nullable = false, length = 12)
    private String phoneNumber;

    @NotNull(message = "Number of persons is required")
    @Min(value = 1, message = "Reservation must be for at least 1 person")
    @Max(value = 20, message = "Reservation must be for max 20 people")
    @Column(nullable = false)
    private Integer numberOfPersons;

    @NotNull(message = "Start time is required")
    @Column(nullable = false)
    private LocalDateTime reservedFrom;

    @NotNull(message = "End time is required")
    @Column(nullable = false)
    private LocalDateTime reservedTo;

    @NotBlank(message = "Table identifier is required")
    @Pattern(regexp = "^(T-0[1-9]|T-[1-9]\\d)$", message = "Table id must match T-01 to T-99")
    @Column(nullable = false, length = 4)
    private String tableId;

    private static final LocalTime OPEN_FROM = LocalTime.of(13, 0);
    private static final LocalTime OPEN_TO = LocalTime.of(22, 0);
    private static final long MAX_RESERVATION_MINUTES = 120;

    @JsonIgnore
    @AssertTrue(message = "Reservation start must be before end time")
    public boolean isTimeRangeValid() {
        if (reservedFrom == null || reservedTo == null) {
            return true;
        }
        return reservedFrom.isBefore(reservedTo);
    }

    @JsonIgnore
    @AssertTrue(message = "Reservation duration must be 2 hours or less")
    public boolean isDurationValid() {
        if (reservedFrom == null || reservedTo == null) {
            return true;
        }
        if (!reservedFrom.isBefore(reservedTo)) {
            return true;
        }
        return Duration.between(reservedFrom, reservedTo).toMinutes() <= MAX_RESERVATION_MINUTES;
    }

    @JsonIgnore
    @AssertTrue(message = "Reservation time must be between 13:00 and 22:00")
    public boolean isWithinOpeningHours() {
        if (reservedFrom == null || reservedTo == null) {
            return true;
        }
        LocalTime fromTime = reservedFrom.toLocalTime();
        LocalTime toTime = reservedTo.toLocalTime();
        return !fromTime.isBefore(OPEN_FROM) && !toTime.isAfter(OPEN_TO);
    }
}
