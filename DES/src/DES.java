
import PluginPackage.Plugin;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.InputStream;
import java.io.OutputStream;

public class DES implements Plugin {
    private SecretKeySpec secretKeySpec = null;

    private Cipher cipher;

    public DES() throws Exception {
        cipher = Cipher.getInstance("DES");
        secretKeySpec = new SecretKeySpec("12345678".getBytes(), "DES");
    }

    @Override
    public void encrypt(InputStream inputStream, OutputStream outputStream) throws Exception {
        byte[] plain = new byte[inputStream.available()];
        int i = inputStream.read(plain);
        if (i != plain.length) throw new Exception("Can't Read Stream");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] bytes=cipher.doFinal(plain);
        outputStream.write(bytes);
    }

    @Override
    public void decrypt(InputStream inputStream, OutputStream outputStream) throws Exception {
        byte[] crypted = new byte[inputStream.available()];
        int i = inputStream.read(crypted);
        if (i != crypted.length) throw new Exception("Can't Read Stream");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        outputStream.write(cipher.doFinal(crypted));
    }

}
