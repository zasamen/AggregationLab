package SeaTransport.ShipAdapter;

import SeaTransport.Ships.Shallop;
import SeaTransport.Ships.Vessel;
import SeaTransport.Ships.VesselComponent.Sail;
import SeaTransport.Ships.Yacht;

public class YachtAdapter implements VesselAdapter{

    private String[] properties;
    private Object[] objects;

    public YachtAdapter(Object... args){
        properties=new String[args.length-2];
        for (int i=0;i<properties.length;i++){
            properties[i]=args[i].toString();
        }
        objects=new Object[2];
        System.arraycopy(args,properties.length-1,objects,0,2);
    }

    @Override
    public Vessel getVessel() {
        Yacht yacht=new Yacht();
        int i=0;
        yacht.setLength(Integer.valueOf(properties[i++]));
        yacht.setWidth(Integer.valueOf(properties[i++]));
        yacht.setCapacity(Integer.valueOf(properties[i++]));
        yacht.setDisplacement(Integer.valueOf(properties[i]));
        yacht.setShallop((Shallop)objects[0]);
        yacht.setSail((Sail)objects[1]);
        return yacht;
    }

    @Override
    public Object getObject() {
        return getVessel();
    }
}
