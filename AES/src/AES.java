import PluginPackage.Plugin;

import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class AES implements Plugin {
    private SecretKeySpec keySpec = null;
    private Cipher cipher;

    public AES() throws Exception{
        cipher = Cipher.getInstance("AES");
        keySpec = new SecretKeySpec("0123456789012345".getBytes(), "AES");
    }

    public  void  encrypt(InputStream inputStream,OutputStream outputStream) throws Exception{
        byte[] plain = new byte[inputStream.available()];
        int i = inputStream.read(plain);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        outputStream.write(cipher.doFinal(plain));
    }
    public  void  decrypt(InputStream inputStream,OutputStream outputStream) throws Exception{
        byte[] crypted = new byte[inputStream.available()];
        int i = inputStream.read(crypted);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        outputStream.write(cipher.doFinal(crypted));
    }

}