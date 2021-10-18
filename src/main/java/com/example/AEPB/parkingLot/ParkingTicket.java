package com.example.AEPB.parkingLot;

import java.util.UUID;

public class ParkingTicket {
    private final String licensePlateNumber;

    public ParkingTicket(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
}
