package SeaTransport.Toolkits;

import PluginPackage.Plugin;

import java.io.*;

public class ProxySerializer implements Serializer{
    private Plugin plugin;
    private Serializer serializer;

    private static ProxySerializer proxySerializer;

    private ProxySerializer(Plugin plugin,Serializer serializer){
        this.plugin=plugin;
        this.serializer=serializer;
    }

    public static ProxySerializer getInstance(Plugin plugin,Serializer serializer) {
        if (proxySerializer != null) {
            proxySerializer.setSerializer(serializer);
            proxySerializer.setPlugin(plugin);
        } else
            proxySerializer=new ProxySerializer(plugin,serializer);
        return proxySerializer;
    }

    private void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }

    private void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void serialize(OutputStream os, Object object) throws IOException {
        if (plugin!=null) {
            File file = new File("pmet");
            try (FileOutputStream nos = new FileOutputStream(file)) {
                serializer.serialize(nos, object);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if (file.exists()) {
                try (FileInputStream is = new FileInputStream(file)) {
                    plugin.encrypt(is, os);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else
                serializer.serialize(os, object);
        }else
            serializer.serialize(os,object);

    }

    @Override
    public Object deserialize(InputStream is) throws ClassNotFoundException, IOException {
        if (plugin == null)
            return serializer.deserialize(is);
        else {
            Object object=null;
            File file = new File("temp");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                plugin.decrypt(is, fos);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (file.exists()) {
                try (FileInputStream nis = new FileInputStream(file)) {
//                    byte[] data = new byte[(int) file.length()];
//                    if (nis.read(data) != 0)
                        object = serializer.deserialize(nis);
                }
                //file.delete();
                return object;
            }
            return serializer.deserialize(is);
        }
    }
}
