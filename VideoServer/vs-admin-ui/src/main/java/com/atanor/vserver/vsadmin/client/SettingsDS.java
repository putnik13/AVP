package com.atanor.vserver.vsadmin.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceEnumField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;

public class SettingsDS  extends DataSource {  
  
    private static SettingsDS instance = null;  
  
    public static SettingsDS getInstance() {  
        if (instance == null) {  
            instance = new SettingsDS("SettingsDS");  
        }  
        return instance;  
    }  
  
    public SettingsDS(String id) {  
  
        setID(id);  
        DataSourceIntegerField pkField = new DataSourceIntegerField("paramsID");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);  
  
        DataSourceTextField settingsName = new DataSourceTextField("settingsName", "Settings Name", 64, true); 
        settingsName.setCanEdit(false);
        
        DataSourceTextField settingsValue = new DataSourceTextField("settingsValue", "Settings Value", 256, true);  
  
        DataSourceTextField descriptionField = new DataSourceTextField("description", "Description", 2000);  
         
        DataSourceBooleanField activeField = new DataSourceBooleanField("active", "Active");  
        activeField.setHidden(true);  
         
        setFields(pkField, settingsName, settingsValue, descriptionField, activeField);  
  
        setClientOnly(true);  
        setTestData(RecordData.getRecords());  
    }  

}
