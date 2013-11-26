package com.atanor.vserver.vsadmin.client;

import java.util.Date;  
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class SettingsRecord extends ListGridRecord {  
  
    public SettingsRecord() {  
    }  
  
    public SettingsRecord(int itemID, String settingsName, String settingsValue, String description, Boolean active) {  
        setItemID(itemID);  
        setSettingsName(settingsName);  
        setSettingsValue(settingsValue);  
        setDescription(description);  
        setActive(active);  
        }  
  
    /** 
     * Set the itemID. 
     * 
     * @param itemID the itemID 
     */  
    public void setItemID(int itemID) {  
        setAttribute("itemID", itemID);  
    }  
  
    /** 
     * Return the itemID. 
     * 
     * @return the itemID 
     */  
    public int getItemID() {  
        return getAttributeAsInt("itemID");  
    }  
  
  
    /** 
     * Set the item. 
     * 
     * @param item the item 
     */  
    public void setSettingsName(String settingsName) {  
        setAttribute("settingsName", settingsName);  
    }  
  
    /** 
     * Return the item. 
     * 
     * @return the item 
     */  
    public String getSettingsName() {  
        return getAttribute("settingsName");  
    }  
  
    /** 
     * Set the SKU. 
     * 
     * @param SKU the SKU 
     */  
    public void setSettingsValue(String settingsValue) {  
        setAttribute("settingsValue", settingsValue);  
    }  
  
    /** 
     * Return the SKU. 
     * 
     * @return the SKU 
     */  
    public String getSettingsValue() {  
        return getAttribute("settingsValue");  
    }  
  
    /** 
     * Set the description. 
     * 
     * @param description the description 
     */  
    public void setDescription(String description) {  
        setAttribute("description", description);  
    }  
  
    /** 
     * Return the description. 
     * 
     * @return the description 
     */  
    public String getDescription() {  
        return getAttribute("description");  
    }  
  
    /** 
     * Set the category. 
     * 
     * @param category the category 
     */  
    public void setCategory(String category) {  
        setAttribute("category", category);  
    }  
  
    /** 
     * Return the category. 
     * 
     * @return the category 
     */  
    public String getCategory() {  
        return getAttribute("category");  
    }  
  
    /** 
     * Set the units. 
     * 
     * @param units the units 
     */  
    public void setUnits(String units) {  
        setAttribute("units", units);  
    }  
  
    /** 
     * Return the units. 
     * 
     * @return the units 
     */  
    public String getUnits() {  
        return getAttribute("units");  
    }  
  
    /** 
     * Set the unitCost. 
     * 
     * @param unitCost the unitCost 
     */  
    public void setUnitCost(Double unitCost) {  
        setAttribute("unitCost", unitCost);  
    }  
  
    /** 
     * Return the unitCost. 
     * 
     * @return the unitCost 
     */  
    public Float getUnitCost() {  
        return getAttributeAsFloat("unitCost");  
    }  
  
    /** 
     * Set the inStock. 
     * 
     * @param inStock the inStock 
     */  
    public void setActive(Boolean active) {  
        setAttribute("active", active);  
    }  
  
    /** 
     * Return the inStock. 
     * 
     * @return the inStock 
     */  
    public Boolean getActive() {  
        return getAttributeAsBoolean("active");  
    }  
 

}
