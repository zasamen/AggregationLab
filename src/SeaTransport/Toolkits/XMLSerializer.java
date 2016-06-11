package SeaTransport.Toolkits;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class XMLSerializer implements Serializer {

    @Override
    public void serialize(OutputStream os, Object object) throws IOException {
        XMLEncoder xmlEncoder=new XMLEncoder(os);
        xmlEncoder.writeObject(object);
        xmlEncoder.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(InputStream is) throws ClassNotFoundException, IOException {
        XMLDecoder xmlDecoder=new XMLDecoder(is);
        Object object=xmlDecoder.readObject();
        xmlDecoder.close();
        return object;
    }
}
