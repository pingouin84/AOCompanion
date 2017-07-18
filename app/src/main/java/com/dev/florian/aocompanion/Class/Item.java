package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 18/07/2017.
 */

public class Item {
    private String type;
    private int count,quality;

    public static Item parse(JSONObject object) {
        Item item = new Item();
        if (object!=null){
            item.type = object.optString("Type");
            item.count = object.optInt("Count");
            item.quality = object.optInt("Quality");
        }
        return item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }
}
