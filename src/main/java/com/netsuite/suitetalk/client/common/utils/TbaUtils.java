package com.netsuite.suitetalk.client.common.utils;

import com.netsuite.suitetalk.client.common.SignatureAlgorithm;
import com.netsuite.suitetalk.client.common.authentication.TokenPassport;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

@ParametersAreNonnullByDefault
public final class TbaUtils {
   private static final String DELIMITER = "&";

   private TbaUtils() {
      super();
   }

   public static String getBaseString(TokenPassport tokenPassport) {
      return getBaseString(tokenPassport.getAccount(), tokenPassport.getConsumerKey(), tokenPassport.getToken(), tokenPassport.getNonce(), tokenPassport.getTimestamp());
   }

   public static String getBaseString(String account, String consumerKey, String token, String nonce, long timestamp) {
      String[] parameters = new String[]{account, consumerKey, token, nonce, String.valueOf(timestamp)};

      for(int i = 0; i < parameters.length; ++i) {
         try {
            parameters[i] = URLEncoder.encode(parameters[i], "UTF-8");
         } catch (UnsupportedEncodingException var9) {
            var9.printStackTrace();
         }
      }

      return CommonUtils.concatenateStrings("&", parameters);
   }

   public static String getKey(String consumerSecret, String tokenSecret) {
      return CommonUtils.concatenateStrings("&", percentEncode(consumerSecret), percentEncode(tokenSecret));
   }

   public static String getSignature(String key, String baseString, SignatureAlgorithm signatureAlgorithm) {
      try {
         SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), signatureAlgorithm.getJavaFormat());
         Mac messageAuthenticationCode = Mac.getInstance(signatureAlgorithm.getJavaFormat());
         messageAuthenticationCode.init(signingKey);
         byte[] rawHmac = messageAuthenticationCode.doFinal(baseString.getBytes());
         return Base64.getEncoder().encodeToString(rawHmac);
      } catch (InvalidKeyException | NoSuchAlgorithmException var6) {
         throw new RuntimeException(var6);
      }
   }

   public static String percentEncode(String string) {
      try {
         return URLEncoder.encode(string, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
      } catch (UnsupportedEncodingException var2) {
         var2.printStackTrace();
         return null;
      }
   }
}
