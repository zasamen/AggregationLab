package SeaTransport.Ships;

import SeaTransport.Ships.VesselComponent.Sail;

public class Yacht extends Boat {
    private Sail sail;

    public Sail getSail() {
        return sail;
    }

    public void setSail(Sail sail) {
        this.sail = sail;
    }
}
