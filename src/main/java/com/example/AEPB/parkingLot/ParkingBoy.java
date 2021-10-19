package com.example.AEPB.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;
    static final int PARKING_LOT_COUNT = 10;

    public ParkingBoy() {
        parkingLots = new ArrayList<>(PARKING_LOT_COUNT);
        for(int i = 0; i < PARKING_LOT_COUNT; i++) {
            parkingLots.add(new ParkingLot());
        }
    }

    public Vehicle pickUpVehicle(ParkingTicket ticket) {
        return null;
    }

    public ParkingTicket park(Vehicle vehicle) {
        return null;
    }
}
