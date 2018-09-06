package com.netsuite.suitetalk.client.common.authentication;

import com.netsuite.suitetalk.client.common.SignatureAlgorithm;
import com.netsuite.suitetalk.client.common.utils.CommonUtils;
import com.netsuite.suitetalk.client.common.utils.TbaUtils;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;

public class TokenPassport {
   private String account;
   private String consumerKey;
   private String token;
   private String nonce;
   private Long timestamp;
   private SignatureAlgorithm signatureAlgorithm;
   private String signature;
   private String consumerSecret;
   private String tokenSecret;
   private boolean automaticallyUpdated;

   public TokenPassport() {
      super();
      this.signatureAlgorithm = SignatureAlgorithm.HMAC_SHA256;
   }

   @ParametersAreNonnullByDefault
   public TokenPassport(String account, String consumerKey, String token, String nonce, Long timestamp, SignatureAlgorithm signatureAlgorithm, String signature) {
      super();
      this.signatureAlgorithm = SignatureAlgorithm.HMAC_SHA256;
      this.account = account;
      this.consumerKey = consumerKey;
      this.token = token;
      this.nonce = nonce;
      this.timestamp = timestamp;
      this.signatureAlgorithm = signatureAlgorithm;
      this.signature = signature;
      this.automaticallyUpdated = false;
   }

   @ParametersAreNonnullByDefault
   public TokenPassport(String account, String consumerKey, String consumerSecret, String token, String tokenSecret) {
      super();
      this.signatureAlgorithm = SignatureAlgorithm.HMAC_SHA256;
      this.account = account;
      this.consumerKey = consumerKey;
      this.consumerSecret = consumerSecret;
      this.token = token;
      this.tokenSecret = tokenSecret;
      this.automaticallyUpdated = true;
   }

   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
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

   public long getTimestamp() {
      return this.timestamp;
   }

   public void setTimestamp(Long timestamp) {
      this.timestamp = timestamp;
   }

   public SignatureAlgorithm getSignatureAlgorithm() {
      return this.signatureAlgorithm;
   }

   public void setSignatureAlgorithm(SignatureAlgorithm signatureAlgorithm) {
      this.signatureAlgorithm = signatureAlgorithm;
   }

   public String getSignature() {
      return this.signature;
   }

   public void setSignature(String signature) {
      this.signature = signature;
   }

   public void setConsumerSecret(String consumerSecret) {
      this.consumerSecret = consumerSecret;
   }

   public void setTokenSecret(String tokenSecret) {
      this.tokenSecret = tokenSecret;
   }

   public boolean isAutomaticallyUpdated() {
      return this.automaticallyUpdated;
   }

   public void setAutomaticallyUpdated(boolean automaticallyUpdated) {
      this.automaticallyUpdated = automaticallyUpdated;
   }

   public void update() {
      this.setNonce(CommonUtils.getRandomNonce());
      this.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
      this.setSignature(TbaUtils.getSignature(TbaUtils.getKey(this.consumerSecret, this.tokenSecret), TbaUtils.getBaseString(this), this.getSignatureAlgorithm()));
   }
}
