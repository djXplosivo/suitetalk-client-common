package com.netsuite.suitetalk.client.common.authentication;

public class Passport {
   private Credentials credentials;
   private String account;
   private String role;

   public Passport() {
      super();
      this.credentials = new Credentials((String)null, (String)null);
   }

   public Passport(Credentials credentials, String account, String role) {
      super();
      this.credentials = credentials;
      this.account = account;
      this.role = role;
   }

   public Passport(String email, String password, String account, String role) {
      this(new Credentials(email, password), account, role);
   }

   public Credentials getCredentials() {
      return this.credentials;
   }

   public void setCredentials(Credentials credentials) {
      this.credentials = credentials;
   }

   public String getEmail() {
      return this.credentials.getEmail();
   }

   public void setEmail(String email) {
      this.credentials.setEmail(email);
   }

   public String getPassword() {
      return this.credentials.getPassword();
   }

   public void setPassword(String password) {
      this.credentials.setPassword(password);
   }

   public String getAccount() {
      return this.account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getRole() {
      return this.role;
   }

   public void setRole(String role) {
      this.role = role;
   }
}
