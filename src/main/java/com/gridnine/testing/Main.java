package com.gridnine.testing;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.service.impl.FlightServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();
        FlightServiceImpl segmentService = new FlightServiceImpl();
        System.out.println("\nСписок - 1 : Все перелёты и сегменты");
        System.out.println("************************************");
        flights.forEach(System.out::println);
        System.out.println("\nСписок - 2 : Исключить перелеты с вылетом до текущего момента времени");
        System.out.println("*********************************************************************");
        segmentService.minusDepartureTimeBeforeCurrentTime(flights).forEach(System.out::println);
        System.out.println("\nСписок - 3 : Исключить перелеты, в которых имеются сегменты с датой прилёта раньше даты вылета");
        System.out.println("**********************************************************************************************");
        segmentService.minusFlightArrivalDateBeforeDepartureDate(flights).forEach(System.out::println);
        System.out.println("\nСписок - 4 : Исключить перелеты, где общее время, проведённое на земле, превышает два часа ");
        System.out.println("******************************************************************************************");
        segmentService.minusFlightsWithMoreThanTwoHoursOnTheGround(flights).forEach(System.out::println);

    }
}
