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

    private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDcoj+zP5krxL72cbNNg" +
            "ocsVuHSpX9ARkD5rqZ+3pV+GtLCbeTNhJELuip4ADxIpyqZ7Ysjg6JQJUISfw0D94KHiFlQVMpo52HN2llE6cSwuSbA2QMDAb/qiOxe" +
            "F94aMOlIMoeP/uX+CcKXiUsNw+sUDM9Tp0fpCAMCklGVHSp/oDnMhLweyvuMRxSSHDJSbpN7V5+THKq9jxbaSUL6F8ZEnhKVHl7q33M" +
            "+ctEE0eN/75kVd9WlpFmvU0B7bi4D4IY+RgseJnQMdXZxH3hWjJDXt6Bb6zhAQV2fbKDDXaiigTb/Bfy2Sgy2rlngVKUPfiSegMWWkk" +
            "eg+Fa5jwRtdw7vAgMBAAECggEACx+C5p6GMlaw6evBhl7WQb3/lIcTa0IfdHcBndPK5Zikl3jC2DmetmCkU3hvkZob47a+FQRF4pR+S" +
            "EBvf4uy4PJWzl339mHz5FTluzut1tr+wSovpK8p5cERaYz6fYeJu0IQ44mOPshvLwSU7BS8fT5IFYeRcIYe4LpPspzNz7FEOh1bbewI" +
            "D5oQS9WK6oIgnle1wrLDqTr0lbW3l0xE6xs14/F7JxHVFtOz4mFc3eWPEYrREYWTnAoMLqMhVOZKswqX57ne4JO3oA+7bFUuAIicTcJ" +
            "KuQvcQc+ax+OZDHTQXgiRDRELPuxey3oDhSqpxAv3rJbxPlrk5E6faYE2wQKBgQD6Gh6bHd7htfkYbtOoO0lgcNmYT/q1aQbiNHAdfO" +
            "cZeoPtz1N4k1GtJuuwNILvQkqn2bfqL8hHxNLFINWAqETLSZYLLLsSFW0ARaIQ6HlF4KXjteCHqjK+Ciqt+WP4+vM6pi52d35gjj0oC" +
            "eV8oeq8GjNd3b+K+3X7TNATJ1FaiwKBgQDh1jpPPRcG9cCqetmuqGue2/pNHoVDH1TtUCFCO85pRhAmE/Fmm/ivud5RT5eJEDT71y/p" +
            "Q8GjdOk4W0T/y9TJuqGDytnTNW/2L+DuPkzCJ4aDv5CKhDDsoB2kG01LO92SwEkHW8Q1xKzgpgXcP9jqKplb5sy0zD1NOuKbGrV9rQK" +
            "BgQDUXhN5AZ89J4jxj0HYiyceZhTOebNYEWuP7pj44RNXFioqZ67oJqfnELJ298/qm+7KolvB1nsJPf7+9od0bO2ldMGckYnzrA/XeE" +
            "lKdCxuAni6Z7jVWdcmeXmOKsMSKv0X4v53B7ZfBMi3U9fywYaR3OmbHe1EdYvULIBOk0dISwKBgAz5Vq44rzr6IVBp8FmFWXFt58pIZ" +
            "5V8lk9v3AlHEEuh98mIOcUQTtAhmHEJZoY8RR72tMzs1JIEZGWVcLhVSGsyapb8r1jf6Ae3pm9patV4mdS1y6L5hyoA5NMJsn4Q+WC1" +
            "5Twj/HiuTNQ3FX0aueZx/g92ieoVFax7JouMlEKdAoGBANXSlkxIikcBpQgNcI0qEXuDh9naTpx5U1UvQsttqVg7uEtbtjU54MsIWju" +
            "/Vc+pWoM1Cv0hXEtLG7yxBo20PmYZ+qyFGE65nRMPzCjwGcego0noEA1ZxTw6XsI/0Q89dHu+dQTkkvzy19fEHgRsgHxv2vB8eDCBWf" +
            "zNRkPAY/lK";
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
        byte[] data = Base64.getDecoder().decode(apiKey.getBytes());
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(PRIVATE_KEY.getBytes()));
            PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return parseApiKey(new String(cipher.doFinal(data)));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidApiKeyException | InvalidKeySpecException | InvalidKeyException e) {
            throw new InvalidApiKeyException();
        }
    }
}
