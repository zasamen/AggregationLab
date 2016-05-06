package SeaTransport.ShipAdapter;

import SeaTransport.Ships.Cruiser;
import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;
import SeaTransport.Ships.VesselComponent.Armament;

public class CruiserAdapter implements VesselAdapter {
    private String[] properties;
    private Object[] objects;

    public CruiserAdapter(Object... args) {
        properties = new String[6];
        for (int i = 0; i < properties.length - 2; i++) {
            properties[i] = args[i].toString();
        }
        properties[4] = args[4].toString();
        properties[5] = args[7].toString();
        objects = new Object[2];
        objects[0] = args[5];
        objects[1] = args[6];
    }

    @Override
    public Vessel getVessel() {
        Cruiser cruiser = new Cruiser();
        int j, i = j = 0;
        cruiser.setLength(Integer.valueOf(properties[i++]));
        cruiser.setWidth(Integer.valueOf(properties[i++]));
        cruiser.setCapacity(Integer.valueOf(properties[i++]));
        cruiser.setDisplacement(Integer.valueOf(properties[i++]));
        cruiser.setShallop((Shallop) objects[j++]);
        cruiser.setCrew(Integer.valueOf(properties[i++]));
        cruiser.setArmament((Armament) objects[j]);
        cruiser.setCount(Integer.valueOf(properties[i]));
        return cruiser;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
