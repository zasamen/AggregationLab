package SeaTransport.ShipAdapter.DeviceAdapter;

import SeaTransport.Ships.VesselComponent.Armament;
import SeaTransport.Ships.VesselComponent.Device;

public class ArmamentAdapter implements DeviceAdapter{

    private String[] properties;

    public ArmamentAdapter(Object... args){
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
        Armament armament=new Armament();
        int i=0;
        armament.setModel(properties[i++]);
        armament.setCaliber(Integer.valueOf(properties[i]));
        return armament;
    }

    @Override
    public Object getObject() {
        return getDevice();
    }

}
