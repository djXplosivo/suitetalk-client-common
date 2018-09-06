package com.netsuite.suitetalk.client.common.contract;

import com.netsuite.suitetalk.client.common.EndpointVersion;
import java.net.URL;

public interface EndpointInfo {
   URL getEndpointUrl();

   EndpointVersion getEndpointVersion();

   String getMessagesUrn();
}
