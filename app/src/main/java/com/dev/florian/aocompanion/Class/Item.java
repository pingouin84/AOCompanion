package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 18/07/2017.
 */

public class Item {
    private String uniqueName,localizedName,localizedDescriptions,categoryId,slotType,itemType,type;
    private int tier,level,itemPower,quality;

    public static Item parseKill(JSONObject object) {
        Item item = new Item();
        if (object!=null){
            item.type = object.optString("Type");
            item.quality = object.optInt("Quality");
        }
        return item;
    }

    public static Item parseItem(JSONObject object) {
        Item item = new Item();
        if (object!=null){
            item.uniqueName = object.optString("uniqueName");
            item.localizedName = object.optJSONObject("localizedNames").optString("FR-FR");
            item.localizedDescriptions = object.optJSONObject("localizedDescriptions").optString("FR-FR");
            item.categoryId = object.optString("categoryId");
            item.slotType = object.optString("slotType");
            item.itemType = object.optString("itemType");

            item.tier = object.optInt("tier");
            item.level = object.optInt("level");
            item.itemPower = object.optInt("itemPower");
        }
        return item;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedDescriptions() {
        return localizedDescriptions;
    }

    public void setLocalizedDescriptions(String localizedDescriptions) {
        this.localizedDescriptions = localizedDescriptions;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getItemPower() {
        return itemPower;
    }

    public void setItemPower(int itemPower) {
        this.itemPower = itemPower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
