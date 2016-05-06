package SeaTransport.ShipAdapter.DeviceAdapter;

import SeaTransport.Ships.VesselComponent.Aircraft;
import SeaTransport.Ships.VesselComponent.Device;

public class AircraftAdapter implements DeviceAdapter{

    private String[] properties;

    public AircraftAdapter(Object... args){
        if (args==null) return;
        properties=new String[args.length];
        for (int i=0;i<properties.length;i++){
            properties[i]=args[i].toString();
        }
    }

    @Override
    public Device getDevice() {
        if (properties==null)
            return null;
        Aircraft aircraft=new Aircraft();
        int i=0;
        aircraft.setWeight(Integer.valueOf(properties[i++]));
        aircraft.setModel(properties[i]);
        return aircraft;
    }

    @Override
    public Object getObject() {
        return getDevice();
    }

}
