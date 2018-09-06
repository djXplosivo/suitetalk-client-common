package com.netsuite.suitetalk.client.common;

public enum SignatureAlgorithm {
   HMAC_SHA1("HmacSHA1", "HMAC-SHA1"),
   HMAC_SHA256("HmacSHA256", "HMAC-SHA256");

   private final String javaFormat;
   private final String netSuiteFormat;

   private SignatureAlgorithm(String javaFormat, String netSuiteFormat) {
      this.javaFormat = javaFormat;
      this.netSuiteFormat = netSuiteFormat;
   }

   public String getJavaFormat() {
      return this.javaFormat;
   }

   public String getNetSuiteFormat() {
      return this.netSuiteFormat;
   }
}
