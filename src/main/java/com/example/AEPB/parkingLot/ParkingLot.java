package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.DuplicateCarException;
import com.example.AEPB.exception.FullParkingLotException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ParkingLot {

    public static final int MAX_CAPACITY = 50;
    private final int parkingLotIndex;

    private Map<ParkingTicket, Vehicle> parkingRecord;

    public ParkingLot(int parkingLotIndex) {
        this.parkingRecord = new HashMap<>();
        this.parkingLotIndex = parkingLotIndex;
    }

    public ParkingTicket parkCar(Vehicle vehicle) {
        if (Objects.isNull(vehicle)) {
            throw new IllegalArgumentException("Illegal LicensePlateNumber!");
        }
        if (parkingRecord.containsValue(vehicle)) {
            throw new DuplicateCarException("Can not park same car twice!");
        }
        if (parkingRecord.size() == MAX_CAPACITY) {
            throw new FullParkingLotException("the parking lot is full!");
        }
        ParkingTicket ticket = new ParkingTicket(parkingLotIndex);
        parkingRecord.put(ticket, vehicle);
        return ticket;
    }

    public Vehicle pickUpCar(ParkingTicket ticket) {
        if (ticket == null || !parkingRecord.containsKey(ticket)) {
            throw new IllegalArgumentException("Illegal Parking Ticket!");
        }

        return parkingRecord.remove(ticket);
    }

    public Map<ParkingTicket, Vehicle> getParkingRecord() {
        return parkingRecord;
    }

    public int getEmptyLot() {
        return MAX_CAPACITY - parkingRecord.size();
    }
}


