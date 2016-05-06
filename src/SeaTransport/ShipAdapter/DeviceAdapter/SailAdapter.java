package SeaTransport.ShipAdapter.DeviceAdapter;

import SeaTransport.Ships.VesselComponent.Device;
import SeaTransport.Ships.VesselComponent.Sail;

public class SailAdapter implements DeviceAdapter {

    private String[] properties;

    public SailAdapter(Object... args){
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
        Sail sail=new Sail();
        int i=0;
        sail.setHeight(Integer.valueOf(properties[i++]));
        sail.setWidth(Integer.valueOf(properties[i]));
        return sail;
    }

    @Override
    public Object getObject() {
        return getDevice();
    }

}
