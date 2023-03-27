package ru.shorokhova.solution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Ticket {

    private String origin;

    @JsonSetter("origin_name")
    private String originName;

    private String destination;

    @JsonSetter("destination_name")
    private String destinationName;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonSetter("departure_date")
    private LocalDate departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonSetter("departure_time")
    private LocalTime departureTime;

    @JsonFormat(pattern = "dd.MM.yy")
    @JsonSetter("arrival_date")
    private LocalDate arrivalDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "H:mm")
    @JsonSetter("arrival_time")
    private LocalTime arrivalTime;

    private String carrier;

    private int stops;
    private int price;

}
