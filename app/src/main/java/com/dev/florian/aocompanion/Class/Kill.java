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
    private int numberOfParticipants,groupMemberCount,EventId,damageTotal,TotalVictimKillFame;
    private Player killer, victim;
    private List<Player> participants,groupMembers;

    public static Kill parse(JSONObject object) {
        Kill kill = new Kill();
        kill.timeStamp = object.optString("TimeStamp");
        kill.killer = Player.parse(object.optJSONObject("Killer"));
        kill.victim = Player.parse(object.optJSONObject("Victim"));
        kill.numberOfParticipants = object.optInt("numberOfParticipants");
        kill.groupMemberCount = object.optInt("groupMemberCount");
        kill.EventId = object.optInt("EventId");
        kill.TotalVictimKillFame = object.optInt("TotalVictimKillFame");

        JSONArray array = object.optJSONArray("GroupMembers");
        kill.groupMembers = new ArrayList<>();
        for (int x = 0;x<array.length();x++){
            Player player = Player.parse(array.optJSONObject(x));
            kill.groupMembers.add(player);
        }

        array = object.optJSONArray("Participants");
        if (kill.numberOfParticipants>0){
            kill.participants = new ArrayList<>();
            for (int x = 0;x<array.length();x++){
                Player player = Player.parse(array.optJSONObject(x));
                kill.participants.add(player);
                kill.damageTotal+=player.getDamageDone();
            }

            for (Player player : kill.participants) {
                if (kill.damageTotal > 0)
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

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public int getGroupMemberCount() {
        return groupMemberCount;
    }

    public int getEventId() {
        return EventId;
    }

    public int getDamageTotal() {
        return damageTotal;
    }

    public int getTotalVictimKillFame() {
        return TotalVictimKillFame;
    }

    public Player getKiller() {
        return killer;
    }

    public Player getVictim() {
        return victim;
    }

    public List<Player> getParticipants() {
        return participants;
    }

    public List<Player> getGroupMembers() {
        return groupMembers;
    }
}
