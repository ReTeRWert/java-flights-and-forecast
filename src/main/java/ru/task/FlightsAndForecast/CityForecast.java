package ru.task.FlightsAndForecast;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CityForecast {
    private int time;
    private int wind;
    private int visibility;
}
