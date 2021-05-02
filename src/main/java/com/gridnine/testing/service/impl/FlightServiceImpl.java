package com.gridnine.testing.service.impl;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.FlightBuilder;
import com.gridnine.testing.model.Segment;
import com.gridnine.testing.service.FlightService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class FlightServiceImpl implements FlightService {

    @Override
    public List<Flight> minusDepartureTimeBeforeCurrentTime(List<Flight> flights)  {

        List<Flight> newFlight = FlightBuilder.createFlights();
        for(Flight flight : flights){
            for (Segment segment : flight.getSegments()){
                if(segment.getDepartureDate().isBefore(LocalDateTime.now())) {
                    newFlight.remove(flight);
                }
            }
        }
        return newFlight;
    }

    @Override
    public List<Flight> minusFlightArrivalDateBeforeDepartureDate(List<Flight> flights) {

        List <Flight> newFlights = new ArrayList<>(flights);
        for(Flight flight : flights){
            for (Segment segment : flight.getSegments()){
                if(segment.getArrivalDate().isBefore(segment.getDepartureDate())){
                    newFlights.remove(flight);
                }
            }
        }
        return newFlights;
    }

    @Override
    public List<Flight> minusFlightsWithMoreThanTwoHoursOnTheGround(List<Flight> flights) {

        List <Flight> newFlights = new ArrayList<>(flights);
        for(Flight flight : flights){
            for (int i = 1; i < flight.getSegments().size(); i++) {
                if (ChronoUnit.HOURS.between(flight.getSegments().get(i-1).getArrivalDate(),
                        flight.getSegments().get(i).getDepartureDate()) >= 2) {
                    newFlights.remove(flight);
                }
            }
        }
        return newFlights;
    }
}

