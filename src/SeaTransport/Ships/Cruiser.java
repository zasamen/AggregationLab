package SeaTransport.Ships;

import SeaTransport.Ships.VesselComponent.Armament;

public class Cruiser extends Ship{
    private Armament armament;
    private int count;

    public Armament getArmament() {
        return armament;
    }

    public int getCount() {
        return count;
    }

    public void setArmament(Armament armament) {
        this.armament = armament;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
