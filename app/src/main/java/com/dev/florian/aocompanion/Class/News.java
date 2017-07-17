package com.dev.florian.aocompanion.Class;

import org.jsoup.nodes.Element;

import java.io.Serializable;

/**
 * Created by flori on 14/07/2017.
 */

public class News {
    private String titre,date, imageUrl, resume,url;

    public static News parse(Element el) {
        News news = new News();
        news.url = el.attr("href");
        news.titre = el.select("h3").text();
        news.imageUrl = el.select("div.news-item__image img").attr("src");
        news.date = el.select("div.news-item__meta > span").text();
        news.resume = el.select("div.news-item__body").text();
        return news;
    }

    public String getTitre() {
        return titre;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getResume() {
        return resume;
    }

    public String getUrl() {
        return url;
    }
}
