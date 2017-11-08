package com.dev.florian.aocompanion.Class;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by flori on 25/07/2017.
 */

public class Battle {
    private String startTime;
    private int id,totalFame,totalKills,totalPlayers;
    private List<Player> players;
    private List<Guild> guilds;

    public static Battle parse(JSONObject object) {
        Battle battle = new Battle();
        battle.id = object.optInt("id");
        battle.startTime = object.optString("startTime");
        battle.totalFame = object.optInt("totalFame");
        battle.totalKills = object.optInt("totalKills");

        JSONArray array = object.optJSONArray("guilds");
        battle.guilds = new ArrayList<>();
        for (int x = 0; x < array.length();x++) {
            battle.guilds.add(Guild.parse(array.optJSONObject(x)));
        }

        array = object.optJSONArray("players");
        battle.players = new ArrayList<>();
        for (int x = 0; x < array.length();x++) {
            battle.players.add(Player.parse(array.optJSONObject(x)));
        }

        battle.totalFame = battle.players.size();

        return battle;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getId() {
        return id;
    }

    public int getTotalFame() {
        return totalFame;
    }

    public int getTotalKills() {
        return totalKills;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Guild> getGuilds() {
        return guilds;
    }
}
