package com.mumu.Online.Exam.System.utils;

import com.mumu.Online.Exam.System.exception.ApiKeyExpiredException;
import com.mumu.Online.Exam.System.exception.InvalidApiKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;

public class ApiKeyUtil {

    // TODO: Generate your own private key and replace the following string.
    private static final String PRIVATE_KEY = "";
    private static final String DATE_TIME_FORMAT = "ddMMyyyy";

    private static String parseApiKey(String apiKey) throws ApiKeyExpiredException, InvalidApiKeyException {
        String[] fields = apiKey.split("_");
        String product = fields[0];
        if (!Objects.equals(product, "OES")) {
            throw new InvalidApiKeyException();
        }
        String customer = fields[1];
        String expirationDateStr = fields[2];

        Date expirationDate;
        try {
            expirationDate = new SimpleDateFormat(DATE_TIME_FORMAT).parse(expirationDateStr);
        } catch (ParseException e) {
            throw new InvalidApiKeyException();
        }

        if (expirationDate.before(new Date())) {
            throw new ApiKeyExpiredException();
        }

        return customer;
    }

    public static String decode(String apiKey) {
        return apiKey;
    /*    byte[] data = Base64.getDecoder().decode(apiKey.getBytes());
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(PRIVATE_KEY.getBytes()));
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return parseApiKey(new String(cipher.doFinal(data)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidApiKeyException | InvalidKeySpecException | InvalidKeyException e) {
            throw new InvalidApiKeyException();
        }*/
    }
}
