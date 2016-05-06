package SeaTransport.ShipAdapter;

import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;

public class ShallopAdapter implements VesselAdapter{

    private String[] properties;

    public ShallopAdapter(Object... args){
        if (args==null) return;
        properties=new String[args.length];
        for (int i=0;i<properties.length;i++){
            properties[i]=args[i].toString();
        }
    }

    @Override
    public Vessel getVessel() {
        if (properties==null)
            return null;
        Shallop shallop=new Shallop();
        int i=0;
        shallop.setLength(Integer.valueOf(properties[i++]));
        shallop.setWidth(Integer.valueOf(properties[i++]));
        shallop.setCapacity(Integer.valueOf(properties[i++]));
        shallop.setDisplacement(Integer.valueOf(properties[i++]));
        shallop.setType(properties[i]);
        return shallop;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
