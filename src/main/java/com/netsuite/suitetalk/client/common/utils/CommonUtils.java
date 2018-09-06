package com.netsuite.suitetalk.client.common.utils;

import com.netsuite.suitetalk.client.common.EndpointVersion;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@ParametersAreNonnullByDefault
public final class CommonUtils {
   private static final Pattern SESSION_ID_PATTERN = Pattern.compile(".*JSESSIONID=(.*?)(;|\\s|$).*");

   private CommonUtils() {
      super();
   }

   public static String getMessagesUrn(EndpointVersion endpointVersion) {
      return "urn:messages_" + endpointVersion + ".platform.webservices.netsuite.com";
   }

   public static URL composeUrl(String host, int port) {
      return composeUrl(getDefaultProtocol(port), host, port);
   }

   public static URL composeUrl(String protocol, String host, int port) {
      return composeUrl(protocol, host, port, (String)null);
   }

   public static URL composeUrl(String host, int port, String query) {
      return composeUrl(getDefaultProtocol(port), host, port, query);
   }

   public static URL composeUrl(String protocol, String host, int port, @Nullable String query) {
      try {
         return new URL(protocol + "://" + host + ':' + port + (query == null ? "" : "/?" + query));
      } catch (MalformedURLException var5) {
         throw new IllegalArgumentException(var5);
      }
   }

   public static String getDefaultProtocol(int port) {
      return port == 443 ? "https" : "http";
   }

   public static String parseSessionIdFromCookie(String cookieValue) {
      Matcher matcher = SESSION_ID_PATTERN.matcher(cookieValue);
      return matcher.matches() ? matcher.replaceAll("$1") : null;
   }

   public static String getCookieWithoutSessionId(String cookieValue) {
      return cookieValue.replaceAll("JSESSIONID=" + parseSessionIdFromCookie(cookieValue) + ";?\\s*", "");
   }

   public static String formatXml(String xml, int indentationSpaces) {
      String encoding = "UTF-8";
      String xmlForProcessing = xml.replaceAll(">\\s+<", "><");

      try {
         Transformer transformer = TransformerFactory.newInstance().newTransformer();
         transformer.setOutputProperty("encoding", "UTF-8");
         transformer.setOutputProperty("method", "xml");
         transformer.setOutputProperty("indent", "yes");
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indentationSpaces));
         Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlForProcessing.getBytes("UTF-8")));
         StreamResult result = new StreamResult(new StringWriter());
         transformer.transform(new DOMSource(document), result);
         return result.getWriter().toString();
      } catch (IOException | SAXException | TransformerException | ParserConfigurationException var7) {
         var7.printStackTrace();
         return xml;
      }
   }

   public static String formatXml(String xml) {
      return formatXml(xml, 2);
   }

   public static boolean isEmptyString(@Nullable String string) {
      return string == null || string.isEmpty();
   }

   public static String getLogMessage(String soapMessage, boolean isRequest) {
      String mark = "====================";
      String type = isRequest ? "Request" : "Response";
      String formattedXml = formatXml(soapMessage);
      return "\n==================== Beginning of " + type + ' ' + "====================" + '\n' + formattedXml + (formattedXml.endsWith("\n") ? "" : '\n') + "====================" + " End of " + type + ' ' + "====================";
   }

   public static String concatenateStrings(String delimiter, String... strings) {
      if (strings != null && strings.length != 0) {
         StringBuilder sb = new StringBuilder(strings[0]);

         for(int i = 1; i < strings.length; ++i) {
            sb.append(delimiter).append(strings[i]);
         }

         return sb.toString();
      } else {
         return "";
      }
   }

   public static String bytesToHexString(byte[] bytes) {
      char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      int length = bytes.length;
      char[] hexString = new char[length * 2];
      int j = 0;

      for(int i = 0; i < length; ++i) {
         hexString[j++] = digits[(240 & bytes[i]) >>> 4];
         hexString[j++] = digits[15 & bytes[i]];
      }

      return new String(hexString);
   }

   public static String getRandomNonce() {
      return UUID.randomUUID().toString().replace("-", "");
   }
}
