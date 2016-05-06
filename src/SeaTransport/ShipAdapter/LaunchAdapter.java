package SeaTransport.ShipAdapter;

import SeaTransport.Ships.Launch;
import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;
import SeaTransport.Ships.VesselComponent.Engine;
import SeaTransport.Ships.VesselComponent.Sail;
import SeaTransport.Ships.Yacht;

public class LaunchAdapter implements VesselAdapter{
    private String[] properties;
    private Object[] objects;

    public LaunchAdapter(Object... args){
        properties=new String[args.length-2];
        for (int i=0;i<properties.length;i++){
            properties[i]=args[i].toString();
        }
        objects=new Object[2];
        System.arraycopy(args,properties.length,objects,0,2);
    }

    @Override
    public Vessel getVessel() {
        Launch launch=new Launch();
        int i=0;
        launch.setLength(Integer.valueOf(properties[i++]));
        launch.setWidth(Integer.valueOf(properties[i++]));
        launch.setCapacity(Integer.valueOf(properties[i++]));
        launch.setDisplacement(Integer.valueOf(properties[i]));
        launch.setShallop((Shallop)objects[0]);
        launch.setEngine((Engine) objects[1]);
        return launch;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
