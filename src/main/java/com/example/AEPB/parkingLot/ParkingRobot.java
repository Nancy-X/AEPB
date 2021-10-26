package com.example.AEPB.parkingLot;

import com.example.AEPB.exception.FullParkingLotException;

import java.util.List;

public class ParkingRobot {

    private List<ParkingLot> parkingLotList;

    private double vacancyRate = 0;
    private int parkingLotIndex = -1;

    public ParkingRobot(List<ParkingLot> parkingLots) {
        this.parkingLotList = parkingLots;
    }

    public ParkingTicket park(Vehicle vehicle) {
        for (int i = 0; i < parkingLotList.size(); i++) {
            double currentVacancyRate = parkingLotList.get(i).getVacancyRate();
            if ( currentVacancyRate > this.vacancyRate) {
                this.vacancyRate = currentVacancyRate;
                this.parkingLotIndex = i;
            }
        }
        if(vacancyRate == 0){
            throw new FullParkingLotException("All parking lots are full!");
        }

        return parkingLotList.get(parkingLotIndex).park(vehicle);
    }
}
