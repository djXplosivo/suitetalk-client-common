package com.netsuite.suitetalk.client.common.contract;

import javax.xml.soap.SOAPHeaderElement;

public interface SoapHeaderHandler {
   void addSoapHeader(String var1, String var2, Object var3);

   SOAPHeaderElement getSoapHeader(String var1, String var2);

   void removeSoapHeader(String var1, String var2);

   boolean isSoapHeaderSet(String var1, String var2);
}
