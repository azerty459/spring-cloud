package fr.nextoo.micro.order.transformer;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import java.security.NoSuchAlgorithmException;

@Component
public class AES256FileEncryptionTransformer extends FileEncryptionTransformer {

    private static final String ALGORITHM = "AES";
    private static final String CIPHER = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_FACTORY = "PBKDF2WithHmacSHA256";

    public AES256FileEncryptionTransformer() {
        super(ALGORITHM);
    }

    @Override
    protected Cipher getCipherInstance() throws NoSuchPaddingException, NoSuchAlgorithmException {
        return Cipher.getInstance(CIPHER);
    }

    @Override
    protected SecretKeyFactory getSecretKeyFactory() throws NoSuchAlgorithmException {
        return SecretKeyFactory.getInstance(SECRET_KEY_FACTORY);
    }
}
