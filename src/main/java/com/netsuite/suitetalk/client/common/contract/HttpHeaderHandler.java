package com.netsuite.suitetalk.client.common.contract;

public interface HttpHeaderHandler {
   void setHttpHeader(String var1, String var2);

   void unsetHttpHeader(String var1);

   boolean isHttpHeaderSet(String var1);
}
