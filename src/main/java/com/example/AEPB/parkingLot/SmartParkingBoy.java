package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Vehicle vehicle) {
        int parkingLotIndex = -1;
        int emptyLotCount = 0;
        for (int i = 0; i < 10; i++) {
            ParkingLot currentParkingLot = parkingLots.get(i);
            int currentEmptyLot = currentParkingLot.getEmptyLot();
            if (currentEmptyLot > emptyLotCount) {
                parkingLotIndex = i;
                emptyLotCount = currentEmptyLot;
            }
        }
        if (parkingLotIndex == -1) {
            throw new FullParkingLotException("All parking lots are full!");
        }
        return parkingLots.get(parkingLotIndex).park(vehicle);
    }
}
