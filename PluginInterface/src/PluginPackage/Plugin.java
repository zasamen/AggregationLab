package PluginPackage;

import java.io.InputStream;
import java.io.OutputStream;

public interface Plugin {
    void encrypt(InputStream inputStream, OutputStream outputStream) throws Exception;

    void decrypt(InputStream inputStream, OutputStream outputStream) throws Exception;

}
