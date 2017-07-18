package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 17/07/2017.
 */

public class Player {
    private String name,guildName,allianceName,allianceTag;
    private int killFame,damageDone,damage,averageItemPower;
    private Equipment equipment;

    public static Player parse(JSONObject object) {
        Player player = new Player();
        player.name = object.optString("Name");
        player.guildName = object.optString("GuildName");
        player.allianceName = object.optString("AllianceName");
        player.allianceTag = object.optString("AllianceTag");
        player.killFame = object.optInt("KillFame");
        player.damageDone = object.optInt("DamageDone");
        player.averageItemPower = object.optInt("AverageItemPower");
        player.equipment = Equipment.parse(object.optJSONObject("Equipment"));

        return player;
    }

    public String getFullName() {
        if (allianceName!=null&&allianceName.length()>0)
            return name + " [" +allianceName+"] - "+ guildName;
        else
            return name + " "+ guildName;
    }

    public String getFullGuild() {
        if (allianceName!=null&&allianceName.length()>0)
            return "[" +allianceName+"] "+ guildName;
        else
            return guildName;
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

    public int getKillFame() {
        return killFame;
    }

    public void setKillFame(int killFame) {
        this.killFame = killFame;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getDamageDone() {
        return damageDone;
    }

    public void setDamageDone(int damageDone) {
        damageDone = damageDone;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAverageItemPower() {
        return averageItemPower;
    }

    public void setAverageItemPower(int averageItemPower) {
        averageItemPower = averageItemPower;
    }
}
