package SeaTransport.Toolkits;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Serializer {
    void serialize(OutputStream os, Object object) throws IOException;

    @SuppressWarnings("unchecked")
    Object deserialize(InputStream is) throws ClassNotFoundException,IOException;
}
