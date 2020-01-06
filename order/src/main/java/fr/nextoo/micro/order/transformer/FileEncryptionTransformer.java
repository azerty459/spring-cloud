package fr.nextoo.micro.order.transformer;

import fr.nextoo.micro.order.exception.DecryptionException;
import fr.nextoo.micro.order.exception.EncryptionException;
import fr.nextoo.micro.order.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public abstract class FileEncryptionTransformer {

    private static final String SALT = "kyxtib-6Joqsi-gyqnaj";
    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private final String algorithm;

    public FileEncryptionTransformer(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] encrypt(MultipartFile file, String secret) {
        try {
            return encrypt(file.getBytes(), secret);
        } catch (IOException e) {
            throw new FileStorageException("Could not store file. Please try again!", e);
        }
    }

    private byte[] encrypt(byte[] bytes, String secret) {
        try {
            return getCipher(secret, Cipher.ENCRYPT_MODE).doFinal(bytes);
        } catch (Exception e) {
            throw new EncryptionException("error while encrypting file.", e);
        }
    }

    public byte[] decrypt(byte[] bytes, String secret) {
        try {
            return getCipher(secret, Cipher.DECRYPT_MODE).doFinal(bytes);
        } catch (Exception e) {
            throw new DecryptionException("error while decrypting file.", e);
        }
    }

    private Cipher getCipher(String secret, int mode) throws
            NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = getCipherInstance();
        cipher.init(mode, getSecretKeySpec(secret), new IvParameterSpec(IV));
        return cipher;
    }

    private SecretKeySpec getSecretKeySpec(String secret) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return new SecretKeySpec(getSecretKey(secret).getEncoded(), algorithm);
    }

    private SecretKey getSecretKey(String secret) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return getSecretKeyFactory()
                .generateSecret(new PBEKeySpec(secret.toCharArray(), SALT.getBytes(), 65536, 256));
    }

    protected abstract Cipher getCipherInstance() throws NoSuchPaddingException, NoSuchAlgorithmException;

    protected abstract SecretKeyFactory getSecretKeyFactory() throws NoSuchAlgorithmException;
}
