package SeaTransport.Ships;

import SeaTransport.Ships.VesselComponent.Aircraft;

public class AircraftCarrier extends Ship {
    private Aircraft aircraft;
    private int count;

    public Aircraft getAircraft() {
        return aircraft;
    }

    public int getCount() {
        return count;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public void setCount(int count) {
        this.count = count;
    }


}
