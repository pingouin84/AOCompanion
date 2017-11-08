package com.dev.florian.aocompanion;

import com.dev.florian.aocompanion.Class.Item;
import com.dev.florian.aocompanion.Class.Kill;
import com.dev.florian.aocompanion.Class.News;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AlbionOnline {

    public static String domaine = "https://albiononline.com";
    public static String domaine_api = "https://gameinfo.albiononline.com/api/gameinfo";

    public static final String TOP_PVP_KILLS = "TOP_PVP_KILLS";
    public static final String RECENT_KILLS = "RECENT_KILLS";
    public static final String TOP_BATTLES = "TOP_BATTLES";
    public static final String RECENT_BATTLES = "RECENT_BATTLES";
    public static final String NEWS_DEV = "developer";
    public static final String NEWS_COM = "community";

    private Document getDocument(String url) {
        try {
            Document document = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .maxBodySize(0)
                    .get();
            return document;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private String getJson(String url) {
        Connection.Response response;
        try {
             response = Jsoup.connect(url)
                     .ignoreContentType(true)
                     .method(Connection.Method.GET)
                     .maxBodySize(0)
                     .execute();
            return response.body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<News> getNewsList(String param) {
        List<News> newsList = new ArrayList<>();

        Document document =  getDocument(domaine+"/fr/news");
        if (document != null) {
            Elements elements = document.select("div.news--"+param+"_news > a");
            for (Element el : elements) {
                newsList.add(News.parse(el));
            }
            return newsList;
        }
        return null;
    }

    public String getNewsArticle(String url) {
        Document document =  getDocument(domaine+url);
        if (document != null) {
            Elements elements = document.select("div.news__body");

            return elements.outerHtml();
        }
        return null;
    }

    public List<Kill> getKillList(String param) {
        List<Kill> killList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray();
            if (param == TOP_PVP_KILLS)
                array = new JSONArray(getJson(domaine_api+"/events/killfame?range=month&offset=0&limit=21"));
            if (param == RECENT_KILLS)
                array = new JSONArray(getJson(domaine_api+"/events?offset=0&limit=21"));

            for (int x=0; x< array.length();x++){
                JSONObject  object = array.getJSONObject(x);
                killList.add(Kill.parse(object));
            }
            return killList;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<Kill> getBattleList(String param) {
        List<Kill> killList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray();
            if (param == TOP_BATTLES)
                array = new JSONArray(getJson(domaine_api+"/battles?range=month&offset=0&limit=21&sort=totalfame"));
            if (param == RECENT_BATTLES)
                array = new JSONArray(getJson(domaine_api+"/battles?offset=0&limit=21&sort=recent"));

            for (int x=0; x< array.length();x++){
                JSONObject  object = array.getJSONObject(x);
                killList.add(Kill.parse(object));
            }
            return killList;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    public Kill getKill(int param) {
        Kill kill = new Kill();
        try {
            JSONObject object = new JSONObject(getJson(domaine_api+"/events/"+param));
            kill = Kill.parse(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kill;
    }

    public Item getItem(String name) {
        Item item = new Item();

        try {
            JSONObject object = new JSONObject(getJson(domaine_api+"/items/"+name+"/data"));
            item = Item.parseItem(object);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return item;
    }

    public List<Kill> getPlayersFeud(String idKiller, String idVictim) {
        List<Kill> killList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(getJson(domaine_api+"/events/"+idKiller+"/history/"+idVictim));
            for (int x=0; x< array.length();x++){
                JSONObject  object = array.getJSONObject(x);
                killList.add(Kill.parse(object));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return killList;
    }

    public List<Kill> getGuildFeud(String idGuildKiller, String idGuildVictim) {
        List<Kill> killList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(getJson(domaine_api+"/guilds/"+idGuildKiller+"/feud/"+idGuildVictim));
            for (int x=0; x< array.length();x++){
                JSONObject  object = array.getJSONObject(x);
                killList.add(Kill.parse(object));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return killList;
    }
}
