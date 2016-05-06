package SeaTransport.Ships;

import SeaTransport.Ships.VesselComponent.Engine;

public class Launch extends Boat {
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
