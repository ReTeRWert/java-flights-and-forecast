package ru.task.FlightsAndForecast;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String no;
    private Integer departure;
    private String from;
    private String to;
    private Integer duration;

    @Override
    public String toString() {
        return getNo() + " | " + getFrom() + " -> " + getTo() + " | ";
    }
}
