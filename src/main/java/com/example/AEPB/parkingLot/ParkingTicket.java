package com.example.AEPB.parkingLot;

import java.util.UUID;

public class ParkingTicket {
    private final String licensePlateNumber;
    private int paringLotNumber;

    public ParkingTicket() {
        licensePlateNumber = "";
    }

    public ParkingTicket(String licensePlateNumber, int paringLotNumber) {
        this.licensePlateNumber = licensePlateNumber;
        this.paringLotNumber = paringLotNumber;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public int getParingLotNumber() {
        return paringLotNumber;
    }

    public void setParingLotNumber(int paringLotNumber) {
        this.paringLotNumber = paringLotNumber;
    }
}
