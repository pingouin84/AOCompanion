package com.dev.florian.aocompanion;

import org.jsoup.nodes.Element;

/**
 * Created by flori on 14/07/2017.
 */

public class News {
    private String titre,date, imageUrl,article,url;

    public static News parse(Element el) {
        News news = new News();
        news.url = el.attr("href");
        news.titre = el.select("h3").text();
        news.imageUrl = el.select("div.news-item__image img").attr("src");
        news.date = el.select("div.news-item__meta > span").text();
        news.article = el.select("div.news-item__body").text();
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

    public String getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }
}
