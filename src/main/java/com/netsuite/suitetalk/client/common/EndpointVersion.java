package com.netsuite.suitetalk.client.common;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.regex.Pattern;

@ParametersAreNonnullByDefault
public class EndpointVersion implements Comparable<EndpointVersion> {
   private static final Pattern ENDPOINT_VERSION_PATTERN = Pattern.compile("\\d{4}_\\d");
   private final String version;

   public EndpointVersion(String endpointVersion) {
      super();
      if (!ENDPOINT_VERSION_PATTERN.matcher(endpointVersion).matches()) {
         throw new IllegalArgumentException("Provided endpoint version is not valid. It must match regular expression " + ENDPOINT_VERSION_PATTERN.toString());
      } else {
         this.version = endpointVersion;
      }
   }

   public String getStringValue() {
      return this.version;
   }

   public double getNumericValue() {
      return Double.parseDouble(this.version.replace("_", "."));
   }

   public boolean isLessThan(EndpointVersion endpointVersion) {
      return this.compareTo(endpointVersion) < 0;
   }

   public boolean isLessThanOrEqualTo(EndpointVersion endpointVersion) {
      return this.compareTo(endpointVersion) <= 0;
   }

   public boolean isEqualTo(EndpointVersion endpointVersion) {
      return this.compareTo(endpointVersion) == 0;
   }

   public boolean isGreaterThanOrEqualTo(EndpointVersion endpointVersion) {
      return this.compareTo(endpointVersion) >= 0;
   }

   public boolean isGreaterThan(EndpointVersion endpointVersion) {
      return this.compareTo(endpointVersion) > 0;
   }

   public String toString() {
      return this.version;
   }

   public int compareTo(EndpointVersion anotherEndpointVersion) {
      return this.version.compareTo(anotherEndpointVersion.version);
   }

   public boolean equals(Object obj) {
      return obj instanceof EndpointVersion && this.version.equals(((EndpointVersion)obj).version);
   }

   public int hashCode() {
      return 31 * this.version.hashCode();
   }
}
