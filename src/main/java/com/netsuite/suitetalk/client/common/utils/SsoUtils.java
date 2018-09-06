package com.netsuite.suitetalk.client.common.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.annotation.ParametersAreNonnullByDefault;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

@ParametersAreNonnullByDefault
public final class SsoUtils {
   private static final BouncyCastleProvider BOUNCY_CASTLE_PROVIDER = new BouncyCastleProvider();

   private SsoUtils() {
      super();
   }

   public static byte[] getToken(String partnerAccount, String partnerUserId, String encryptionKeyFilePath) {
      try {
         return encrypt(Files.readAllBytes(Paths.get(encryptionKeyFilePath)), getToken(partnerAccount, partnerUserId));
      } catch (IOException var4) {
         throw new IllegalArgumentException("File " + encryptionKeyFilePath + " cannot be read", var4);
      }
   }

   public static byte[] getToken(String partnerAccount, String partnerUserId) {
      return CommonUtils.concatenateStrings(" ", partnerAccount, partnerUserId, String.valueOf(System.currentTimeMillis())).getBytes();
   }

   public static String getTokenAsString(String partnerAccount, String partnerUserId, String encryptionKeyFilePath) {
      return CommonUtils.bytesToHexString(getToken(partnerAccount, partnerUserId, encryptionKeyFilePath));
   }

   public static String getTokenAsString(String partnerAccount, String partnerUserId) {
      return CommonUtils.bytesToHexString(getToken(partnerAccount, partnerUserId));
   }

   public static byte[] encrypt(byte[] privateKey, byte[] data) {
      try {
         Signature signature = Signature.getInstance("NONEwithRSA");
         signature.initSign(getPrivateKey(privateKey));
         signature.update(data);
         return signature.sign();
      } catch (SignatureException | InvalidKeyException | NoSuchAlgorithmException var3) {
         throw new IllegalStateException("Signing data with private key was not successful", var3);
      }
   }

   public static PrivateKey getPrivateKey(byte[] privateKey) {
      if (Security.getProvider(BOUNCY_CASTLE_PROVIDER.getName()) == null) {
         Security.addProvider(BOUNCY_CASTLE_PROVIDER);
      }

      try {
         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
         PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
         return keyFactory.generatePrivate(privateKeySpec);
      } catch (InvalidKeySpecException | NoSuchAlgorithmException var3) {
         throw new IllegalStateException("Private key generation failed", var3);
      }
   }
}
