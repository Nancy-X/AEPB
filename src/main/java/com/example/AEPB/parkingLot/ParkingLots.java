package com.example.AEPB.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLots {

    private final List<ParkingLot> parkingLots;
    static final int PARKING_LOT_COUNT = 10;

    public ParkingLots() {
        parkingLots = new ArrayList<>(PARKING_LOT_COUNT);
        for(int i = 0; i < PARKING_LOT_COUNT; i++) {
            parkingLots.add(new ParkingLot(i + 1));
        }
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
