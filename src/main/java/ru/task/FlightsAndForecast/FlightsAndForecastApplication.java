package ru.task.FlightsAndForecast;

import java.io.IOException;

public class FlightsAndForecastApplication {

	public static void main(String[] args) throws IOException {
		ScheduleManager forecastManager = new ScheduleManager();
		forecastManager.getSchedule();
	}
}
