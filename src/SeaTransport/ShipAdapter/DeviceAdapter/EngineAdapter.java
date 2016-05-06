package SeaTransport.ShipAdapter.DeviceAdapter;

import SeaTransport.Ships.VesselComponent.Device;
import SeaTransport.Ships.VesselComponent.Engine;

public class EngineAdapter implements DeviceAdapter{

    private String[] properties;

    public EngineAdapter(Object... args){
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
        Engine engine=new Engine();
        int i=0;
        engine.setVolume(Integer.valueOf(properties[i++]));
        engine.setPower(Integer.valueOf(properties[i++]));
        engine.setConsumption(Integer.valueOf(properties[i]));
        return engine;
    }

    @Override
    public Object getObject() {
        return getDevice();
    }

}
