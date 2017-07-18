package com.dev.florian.aocompanion.Class;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flori on 16/07/2017.
 */

public class Kill {

    private String timeStamp;
    private int numberOfParticipants,EventId,damageTotal,TotalVictimKillFame;
    private Player killer, victim;
    private List<Player> participants;

    public static Kill parse(JSONObject object) {
        Kill kill = new Kill();
        kill.timeStamp = object.optString("TimeStamp");
        kill.killer = Player.parse(object.optJSONObject("Killer"));
        kill.victim = Player.parse(object.optJSONObject("Victim"));
        kill.numberOfParticipants = object.optInt("numberOfParticipants");
        kill.EventId = object.optInt("EventId");
        kill.TotalVictimKillFame = object.optInt("TotalVictimKillFame");

        JSONArray array = object.optJSONArray("Participants");
        if (kill.numberOfParticipants>0){
            kill.participants = new ArrayList<>();
            for (int x = 0;x<array.length();x++){
                Player player = Player.parse(array.optJSONObject(x));
                kill.participants.add(player);
                kill.damageTotal+=player.getDamageDone();
            }

            for (Player player:kill.participants) {
                player.setDamage(player.getDamageDone()*100/kill.damageTotal);
                if (player.getName().equals(kill.killer.getName()))
                    kill.killer.setDamage(player.getDamage());
            }
        }
        else {
            kill.killer.setDamage(100);
        }

        return kill;
    }

    public String getTimeStamp() {
        Timestamp timestamp = Timestamp.valueOf(timeStamp.replace("T"," ").replace("Z"," "));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return formatter.format(new Date(timestamp.getTime()));
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

    public int getTotalVictimKillFame() {
        return TotalVictimKillFame;
    }

    public void setTotalVictimKillFame(int totalVictimKillFame) {
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

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public List<Player> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Player> participants) {
        this.participants = participants;
    }
}
