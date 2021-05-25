/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

/**
 *
 * @author alexander.lopez.o
 */
public class RsaImplementation {

    private static KeyPair llavesRSA;

    public RsaImplementation() throws IOException {
        Security.addProvider(new BouncyCastleProvider());

    }

    public void initKeys() throws IOException {
        KeyPair keyPair = readKeyPair(new File("chatprivate.pem"), "abc123");
        this.llavesRSA = keyPair;

    }

    public static String encrypt(String text) {
        byte[] utf8 = null;
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.ENCRYPT_MODE, llavesRSA.getPublic());
            utf8 = rsa.doFinal(text.getBytes());
            return new String(utf8, "UTF8");
        } catch (Exception ex) {
            Logger.getLogger(RsaImplementation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
    }

    public String decrypt(byte[] buffer) {
        try {
            Cipher rsa;
            rsa = Cipher.getInstance("RSA");
            rsa.init(Cipher.DECRYPT_MODE, llavesRSA.getPrivate());
            byte[] utf8 = rsa.doFinal(buffer);
            return new String(utf8, "UTF8");
        } catch (Exception ex) {
            Logger.getLogger(RsaImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static KeyPair readKeyPair(File privateKey, String keyPassword) throws IOException {
        FileReader fileReader = new FileReader(privateKey);
        PEMParser r = new PEMParser(fileReader);
        PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build(keyPassword.toCharArray());
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        KeyPair kp;
        Object object = r.readObject();
        if (object instanceof PEMEncryptedKeyPair) {
            kp = converter.getKeyPair(((PEMEncryptedKeyPair) object).decryptKeyPair(decProv));
        } else {
            kp = converter.getKeyPair((PEMKeyPair) object);
        }

        try {
            return kp;
        } catch (Exception ex) {
            Logger.getLogger(RsaImplementation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            r.close();
            fileReader.close();
        }
    }

    private static Key readPublicKey(File privateKey, String keyPassword) throws IOException {
        final FileReader fileReader = new FileReader(privateKey);
        final PEMParser r = new PEMParser(fileReader);
        try {
            return (RSAPublicKey) r.readObject();
        } catch (IOException ex) {
            Logger.getLogger(RsaImplementation.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            r.close();
            fileReader.close();
        }
    }

}
