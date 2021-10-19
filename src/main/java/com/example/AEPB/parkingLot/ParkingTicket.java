package com.example.AEPB.parkingLot;

import java.util.UUID;

public class ParkingTicket {
    private final int paringLotNumber;

    public ParkingTicket(int paringLotNumber) {
        this.paringLotNumber = paringLotNumber;
    }

    public int getParingLotNumber() {
        return paringLotNumber;
    }
}
