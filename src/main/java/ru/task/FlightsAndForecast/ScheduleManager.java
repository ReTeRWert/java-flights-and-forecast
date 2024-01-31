package ru.task.FlightsAndForecast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleManager {

    public void getSchedule() throws IOException {

        File file = new File("flights_and_forecast.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Flights flight1 = objectMapper.readValue(file, new TypeReference<>() {
        });

        Flight[] flights = flight1.getFlights();

        Forecast forecast = objectMapper.readValue(file, new TypeReference<>() {
        });

        Map<String, Integer> zones = new HashMap<>();

        zones.put("moscow", 3);
        zones.put("novosibirsk", 7);
        zones.put("omsk", 6);

        for (Flight flight : flights) {
            List<CityForecast> forecasts = List.of(
                    forecast
                            .getForecast()
                            .get(flight.getFrom()));

            CityForecast forecastFrom = forecasts.stream()
                    .filter(f -> f.getTime() == flight.getDeparture())
                    .toList()
                    .get(0);

            if (forecastFrom.getWind() <= 30 && forecastFrom.getVisibility() >= 200) {

                int arrival = getArrivalTime(
                        flight.getDeparture(),
                        flight.getDuration(),
                        zones.get(flight.getFrom()),
                        zones.get(flight.getTo())
                );

                forecasts = List.of(forecast
                        .getForecast()
                        .get(flight.getTo()));

                CityForecast forecastTo = forecasts.stream()
                        .filter(f -> f.getTime() == arrival)
                        .toList()
                        .get(0);

                if (forecastTo.getWind() <= 30 && forecastTo.getVisibility() >= 200) {
                    System.out.println(flight + "по расписанию");
                } else {
                    System.out.println(flight + "отменен");
                }
            } else {
                System.out.println(flight + "отменен");
            }

        }

    }

    private int getArrivalTime(int departure, int duration, int from, int to) {
        int arrivalTime = departure + duration + (to - from);

        if (arrivalTime > 24) {
            arrivalTime -= 24;
        }

        return arrivalTime;
    }
}
