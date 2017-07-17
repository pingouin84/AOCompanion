package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 16/07/2017.
 */

public class PvpKills {

    private String timeStamp, TotalVictimKillFame;
    private int numberOfParticipants;
    private Player killer, victim;

    public static PvpKills parse(JSONObject object) {
        PvpKills pvpKills = new PvpKills();
        pvpKills.timeStamp = object.optString("TimeStamp");
        pvpKills.killer = Player.parse(object.optJSONObject("Killer"));
        pvpKills.victim = Player.parse(object.optJSONObject("Victim"));
        pvpKills.numberOfParticipants = object.optInt("numberOfParticipants");
        pvpKills.TotalVictimKillFame = object.optString("TotalVictimKillFame");
        return pvpKills;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getTotalVictimKillFame() {
        return TotalVictimKillFame;
    }

    public void setTotalVictimKillFame(String totalVictimKillFame) {
        this.TotalVictimKillFame = totalVictimKillFame;
    }

    public Player getKiller() {
        return killer;
    }

    public void setKiller(Player killer) {
        this.killer = killer;
    }

    public Player getVictim() {
        return victim;
    }

    public void setVictim(Player victim) {
        this.victim = victim;
    }
}
