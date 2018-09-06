package com.netsuite.suitetalk.client.common.authentication;

import com.netsuite.suitetalk.client.common.utils.CommonUtils;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class OAuthPassport {
   private static final String DEFAULT_SIGNATURE_METHOD = "PLAINTEXT";
   private static final String HTTP_HEADER_NAME = "Authorization";
   private String token;
   private String consumerKey;
   private String nonce;
   private Long timestamp;
   private String signatureMethod;
   private String signature;
   private boolean automaticallyUpdated;

   public OAuthPassport() {
      super();
   }

   public OAuthPassport(String consumerKey, String token, @Nullable String nonce, @Nullable Long timestamp, String signatureMethod, String signature) {
      super();
      this.consumerKey = consumerKey;
      this.token = token;
      this.nonce = nonce;
      this.timestamp = timestamp;
      this.signatureMethod = signatureMethod;
      this.signature = signature;
      this.automaticallyUpdated = false;
   }

   public OAuthPassport(String consumerKey, String token, String signatureMethod, String signature) {
      this(consumerKey, token, (String)null, (Long)null, signatureMethod, signature);
      this.automaticallyUpdated = true;
   }

   public OAuthPassport(String consumerKey, String token, String signature) {
      this(consumerKey, token, "PLAINTEXT", signature);
   }

   public String getConsumerKey() {
      return this.consumerKey;
   }

   public void setConsumerKey(String consumerKey) {
      this.consumerKey = consumerKey;
   }

   public String getToken() {
      return this.token;
   }

   public void setToken(String token) {
      this.token = token;
   }

   public String getNonce() {
      return this.nonce;
   }

   public void setNonce(String nonce) {
      this.nonce = nonce;
   }

   public Long getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
   }

   public String getSignatureMethod() {
      return this.signatureMethod;
   }

   public void setSignatureMethod(String signatureMethod) {
      this.signatureMethod = signatureMethod;
   }

   public String getSignature() {
      return this.signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public boolean isAutomaticallyUpdated() {
      return this.automaticallyUpdated;
   }

   public void setAutomaticallyUpdated(boolean automaticallyUpdated) {
      this.automaticallyUpdated = automaticallyUpdated;
   }

   public String getOAuthHttpHeaderName() {
      return "Authorization";
   }

   public String getOAuthHttpHeaderValue() {
      if (this.isAutomaticallyUpdated()) {
         this.setNonce(CommonUtils.getRandomNonce());
         this.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
      }

      return "OAuth oauth_consumer_key=\"" + this.getConsumerKey() + "\", oauth_token=\"" + this.getToken() + "\", oauth_nonce=\"" + this.getNonce() + "\", oauth_timestamp=\"" + this.getTimestamp() + "\", oauth_signature_method=\"" + this.getSignatureMethod() + "\", oauth_signature=\"" + this.getSignature() + '"';
   }
}
