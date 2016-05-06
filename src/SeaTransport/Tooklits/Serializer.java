package SeaTransport.Tooklits;

import SeaTransport.Ships.Vessel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

interface Serializer {
    void serialize(FileOutputStream fos, List<Vessel> vesselList) throws IOException;

    @SuppressWarnings("unchecked")
    List<Vessel> deserialize(FileInputStream fis) throws ClassNotFoundException,IOException;
}
