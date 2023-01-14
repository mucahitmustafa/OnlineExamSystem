package com.mumu.Online.Exam.System;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class ApiKeyGenerator {

    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3KI/sz+ZK8S+9nGzTYKHLFbh" +
            "0qV/QEZA+a6mft6VfhrSwm3kzYSRC7oqeAA8SKcqme2LI4OiUCVCEn8NA/eCh4hZUFTKaOdhzdpZROnEsLkmwNkDAwG/6ojsXhfeG" +
            "jDpSDKHj/7l/gnCl4lLDcPrFAzPU6dH6QgDApJRlR0qf6A5zIS8Hsr7jEcUkhwyUm6Te1efkxyqvY8W2klC+hfGRJ4SlR5e6t9zPn" +
            "LRBNHjf++ZFXfVpaRZr1NAe24uA+CGPkYLHiZ0DHV2cR94VoyQ17egW+s4QEFdn2ygw12oooE2/wX8tkoMtq5Z4FSlD34knoDFlpJH" +
            "oPhWuY8EbXcO7wIDAQAB";

    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException,
            InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(PUBLIC_KEY));
        return cipher.doFinal(data.getBytes());
    }

    public static void generatePublicAndPrivateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair pair = generator.generateKeyPair();
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        System.out.println("Private Key: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
        System.out.println("Public Key: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter customer name...");
            String customerName = sc.nextLine();

            System.out.println("Enter expiration date in format 'ddMMyyyy' (Exp: 31122022)...");
            String expirationDate = sc.nextLine();

            String apiKeyData = "OES_" + customerName + "_" + expirationDate;
            String apiKey = Base64.getEncoder().encodeToString(encrypt(apiKeyData));
            StringBuilder hashString = new StringBuilder();
            for (int i = 0; i <= apiKey.length(); i++) {
                hashString.append("#");
            }
            System.out.println(hashString);
            System.out.printf("Generated Online Exam System API key for customer '%s'\n", customerName);
            System.out.println(apiKey);
            System.out.println(hashString);
        } catch (NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | NoSuchPaddingException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }

    }
}
