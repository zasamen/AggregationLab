package SeaTransport.ShipAdapter;

import SeaTransport.Ships.AircraftCarrier;
import SeaTransport.Ships.Cruiser;
import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;
import SeaTransport.Ships.VesselComponent.Aircraft;
import SeaTransport.Ships.VesselComponent.Armament;

public class AircraftCarrierAdapter implements VesselAdapter{
    private String[] properties;
    private Object[] objects;

    public AircraftCarrierAdapter(Object... args) {
        properties = new String[6];
        for (int i = 0; i < properties.length - 2; i++) {
            properties[i] = args[i].toString();
        }
        properties[4] = args[5].toString();
        properties[5] = args[7].toString();
        objects = new Object[2];
        objects[1] = args[4];
        objects[1] = args[6];
    }

    @Override
    public Vessel getVessel() {
        AircraftCarrier aircraftCarrier = new AircraftCarrier();
        int j, i = j = 0;
        aircraftCarrier.setLength(Integer.valueOf(properties[i++]));
        aircraftCarrier.setWidth(Integer.valueOf(properties[i++]));
        aircraftCarrier.setCapacity(Integer.valueOf(properties[i++]));
        aircraftCarrier.setDisplacement(Integer.valueOf(properties[i++]));
        aircraftCarrier.setShallop((Shallop) objects[j++]);
        aircraftCarrier.setCrew(Integer.valueOf(properties[i++]));
        aircraftCarrier.setAircraft((Aircraft) objects[j]);
        aircraftCarrier.setCount(Integer.valueOf(properties[i]));
        return aircraftCarrier;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
