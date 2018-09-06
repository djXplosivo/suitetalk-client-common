package com.netsuite.suitetalk.client.common.contract;

import com.netsuite.suitetalk.client.common.authentication.OAuthPassport;
import com.netsuite.suitetalk.client.common.authentication.Passport;
import com.netsuite.suitetalk.client.common.authentication.SsoPassport;
import com.netsuite.suitetalk.client.common.authentication.TokenPassport;

public interface Authentication {
   Passport getPassport();

   void setPassport(Passport var1);

   void unsetPassport();

   void setRequestLevelCredentials(boolean var1);

   SsoPassport getSsoPassport();

   void setSsoPassport(SsoPassport var1);

   void unsetSsoPassport();

   TokenPassport getTokenPassport();

   void setTokenPassport(TokenPassport var1);

   void unsetTokenPassport();

   OAuthPassport getOAuthPassport();

   void setOAuthPassport(OAuthPassport var1);

   void unsetOAuthPassport();
}
