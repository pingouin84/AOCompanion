package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 17/07/2017.
 */

public class Player {
    private String name,guildName,allianceName,allianceTag;

    public static Player parse(JSONObject object) {
        Player player = new Player();
        player.name = object.optString("Name");
        player.guildName = object.optString("GuildName");
        player.allianceName = object.optString("AllianceName");
        player.allianceTag = object.optString("AllianceTag");
        return player;
    }

    public String getFullName() {
        if (allianceName!=null&&allianceName.length()>0)
            return name + " [" +allianceName+"] "+ guildName;
        else
            return name + " "+ guildName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getAllianceName() {
        return allianceName;
    }

    public void setAllianceName(String allianceName) {
        this.allianceName = allianceName;
    }

    public String getAllianceTag() {
        return allianceTag;
    }

    public void setAllianceTag(String allianceTag) {
        this.allianceTag = allianceTag;
    }
}
