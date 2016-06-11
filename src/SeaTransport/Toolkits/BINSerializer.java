package SeaTransport.Toolkits;

import java.io.*;

public class BINSerializer implements Serializer{
    @Override
    public void serialize(OutputStream os, Object object) throws IOException{
        ObjectOutputStream oos= new ObjectOutputStream(os);
        oos.writeObject(object);
        oos.flush();
        oos.close();
    }
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(InputStream is) throws ClassNotFoundException,IOException {
        ObjectInputStream ois=new ObjectInputStream(is);
        return ois.readObject();
    }
}
