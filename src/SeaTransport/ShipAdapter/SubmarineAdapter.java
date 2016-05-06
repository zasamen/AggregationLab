package SeaTransport.ShipAdapter;

import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Submarine;
import SeaTransport.Ships.Vessel;

public class SubmarineAdapter implements VesselAdapter{
    private String[] properties;
    private Object[] objects;

    public SubmarineAdapter(Object... args) {
        properties = new String[7];
        for (int i = 0; i < properties.length - 3; i++) {
            properties[i] = args[i].toString();
        }
        for (int i = 4; i < properties.length; i++) {
            properties[i] = args[i+1].toString();
        }
        objects = new Object[1];
        objects[1] = args[4];
    }

    @Override
    public Vessel getVessel() {
        Submarine submarine = new Submarine();
        int i = 0;
        submarine.setLength(Integer.valueOf(properties[i++]));
        submarine.setWidth(Integer.valueOf(properties[i++]));
        submarine.setCapacity(Integer.valueOf(properties[i++]));
        submarine.setDisplacement(Integer.valueOf(properties[i++]));
        submarine.setShallop((Shallop) objects[0]);
        submarine.setCrew(Integer.valueOf(properties[i++]));
        submarine.setTorpedoType(properties[i++]);
        submarine.setCount(Integer.valueOf(properties[i]));
        return submarine;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
