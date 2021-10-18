package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.DuplicateCarException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private static final int MAX_CAPACITY = 50;

    private Map<String, ParkingTicket> parkingRecord;

    public ParkingLot() {
        parkingRecord = new HashMap<>();
    }

    public ParkingTicket parkCar(String licensePlateNumber) {
        if (StringUtils.isEmpty(licensePlateNumber)) {
            throw new IllegalArgumentException("Illegal LicensePlateNumber!");
        }
        if (parkingRecord.containsKey(licensePlateNumber)) {
            throw new DuplicateCarException("Can not park same car twice!");
        }
        if (parkingRecord.size() == MAX_CAPACITY) {
            return null;
        }
        ParkingTicket ticket = new ParkingTicket(licensePlateNumber);
        parkingRecord.put(licensePlateNumber, ticket);
        return ticket;
    }

    public boolean pickUpCar(ParkingTicket ticket) {
        if (ticket == null || !parkingRecord.containsKey(ticket.getLicensePlateNumber())) {
            throw new IllegalArgumentException("Illegal Parking Ticket!");
        }
        parkingRecord.remove(ticket.getLicensePlateNumber());

        return true;
    }
}


