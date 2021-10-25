package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.Objects;

public class SmartParkingBoy {

    private final ParkingLots parkingLots;

    public SmartParkingBoy(ParkingLots parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Vehicle vehicle) {
        int parkingLotIndex = -1;
        int emptyLotCount = 0;
        for(int i = 0; i < 10; i++) {
            ParkingLot currentParkingLot = parkingLots.getParkingLots().get(i);
            if(currentParkingLot.getEmptyLot() > emptyLotCount) {
                parkingLotIndex = i;
                emptyLotCount = currentParkingLot.getEmptyLot();
            }
        }
        if (parkingLotIndex == -1) {
            throw new FullParkingLotException("All parking lots are full!");
        }
        return parkingLots.getParkingLots().get(parkingLotIndex).parkCar(vehicle);
    }

    public Vehicle pickup(ParkingTicket parkingTicket) {
        if (Objects.isNull(parkingTicket) ) {
            throw new IllegalArgumentException("Invalid ParkingTicket!");
        }
        int parkingLotNumber = parkingTicket.getParingLotNumber();
        return parkingLots.getParkingLots().get(parkingLotNumber - 1).pickUpCar(parkingTicket);
    }
}
