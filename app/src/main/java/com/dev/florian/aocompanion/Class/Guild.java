package com.dev.florian.aocompanion.Class;

import org.json.JSONObject;

/**
 * Created by flori on 25/07/2017.
 */

public class Guild {
    private String name;
    private int kills,deaths;

    public static Guild parse(JSONObject object) {
        Guild guild = new Guild();

        return guild;
    }
}
