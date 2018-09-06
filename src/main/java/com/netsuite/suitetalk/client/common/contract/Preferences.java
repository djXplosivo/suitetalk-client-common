package com.netsuite.suitetalk.client.common.contract;

public interface Preferences {
   void setDisableMandatoryCustomFieldValidation(boolean var1);

   void setDisableSystemNotesForCustomFields(boolean var1);

   void setIgnoreReadOnlyFields(boolean var1);

   default void setRunServerSuiteScriptAndWorkflowTriggers(boolean runServerSuiteScriptAndWorkflowTriggers) {
      throw new UnsupportedOperationException("Preference runServerSuiteScriptAndWorkflowTriggers is only available from endpoint 2016.2.");
   }

   void setWarningAsError(boolean var1);
}
