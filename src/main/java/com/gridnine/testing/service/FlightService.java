package com.gridnine.testing.service;

import com.gridnine.testing.model.Flight;

import java.util.List;

public interface FlightService {

    List<Flight> minusDepartureTimeBeforeCurrentTime(List<Flight> flights);
    List<Flight> minusFlightArrivalDateBeforeDepartureDate(List<Flight> flights);
    List<Flight> minusFlightsWithMoreThanTwoHoursOnTheGround(List<Flight> flights);

}

