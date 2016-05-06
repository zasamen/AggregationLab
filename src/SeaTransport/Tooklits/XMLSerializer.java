package SeaTransport.Tooklits;

import SeaTransport.Ships.Vessel;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XMLSerializer implements Serializer {

    @Override
    public void serialize(FileOutputStream fos, List<Vessel> vesselList) throws IOException {
        XMLEncoder xmlEncoder=new XMLEncoder(fos);
        xmlEncoder.writeObject(vesselList);
        xmlEncoder.flush();
        xmlEncoder.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Vessel> deserialize(FileInputStream fis) throws ClassNotFoundException, IOException {
        XMLDecoder xmlDecoder=new XMLDecoder(fis);
        return (List<Vessel>)xmlDecoder.readObject();
    }
}
