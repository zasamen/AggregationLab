package SeaTransport.Tooklits;

import SeaTransport.Ships.Vessel;

import java.io.*;
import java.util.List;

public class BinSerializer implements Serializer{
    @Override
    public void serialize(FileOutputStream fos, List<Vessel> vesselList) throws IOException{
        ObjectOutputStream oos= new ObjectOutputStream(fos);
        oos.writeObject(vesselList);
        oos.flush();
        oos.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Vessel> deserialize(FileInputStream fis) throws ClassNotFoundException,IOException {
        ObjectInputStream ois=new ObjectInputStream(fis);
        return (List<Vessel>)ois.readObject();
    }
}
