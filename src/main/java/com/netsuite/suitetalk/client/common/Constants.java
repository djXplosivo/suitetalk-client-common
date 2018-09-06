package com.netsuite.suitetalk.client.common;

import java.util.concurrent.TimeUnit;

public final class Constants {
   public static final String HTTP_PROTOCOL = "http";
   public static final String HTTPS_PROTOCOL = "https";
   public static final int HTTPS_PORT = 443;
   public static final String SERVICES_PATH = "/services/";
   public static final String NETSUITE_PORT_PREFIX = "NetSuitePort_";
   public static final String MESSAGES_URN_PREFIX = "urn:messages_";
   public static final String MESSAGES_URN_SUFFIX = ".platform.webservices.netsuite.com";
   public static final int DEFAULT_HTTP_SOCKET_TIMEOUT;
   public static final String SOAP_HEADER_PREFERENCES = "preferences";
   public static final String SOAP_HEADER_SEARCH_PREFERENCES = "searchPreferences";
   public static final String SOAP_HEADER_APPLICATION_INFO = "applicationInfo";
   public static final String SOAP_HEADER_PASSPORT = "passport";
   public static final String SOAP_HEADER_SSO_PASSPORT = "ssoPassport";
   public static final String SOAP_HEADER_TOKEN_PASSPORT = "tokenPassport";
   public static final String REQUEST_COOKIE_HEADER = "Cookie";
   public static final String RESPONSE_COOKIE_HEADER = "Set-Cookie";
   public static final String SESSION_ID = "JSESSIONID";
   public static final String DEFAULT_ENCODING = "UTF-8";
   public static final String ENDPOINT_VERSION_DELIMITER = "_";

   private Constants() {
      super();
   }

   static {
      DEFAULT_HTTP_SOCKET_TIMEOUT = (int)TimeUnit.MINUTES.toMillis(100L);
   }
}
