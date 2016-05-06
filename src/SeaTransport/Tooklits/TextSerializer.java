package SeaTransport.Tooklits;

import SeaTransport.Ships.Vessel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TextSerializer implements Serializer {

    @Override
    public void serialize(FileOutputStream fos, List<Vessel> vesselList) throws IOException {

    }

    @Override
    public List<Vessel> deserialize(FileInputStream fis) throws ClassNotFoundException, IOException {
        return null;
    }
}
