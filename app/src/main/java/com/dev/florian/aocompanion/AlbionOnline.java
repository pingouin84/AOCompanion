package com.dev.florian.aocompanion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by flori on 14/07/2017.
 */

public class AlbionOnline {

    public static String domaine = "https://albiononline.com";

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

    public List<News> getNews() {
        List<News> newsList = new ArrayList<>();

        Document document;
        document =  getDocument(domaine+"/fr/news");
        if (document != null) {
            Elements elements = document.select("div.news--developer_news > a");
            for (Element el : elements) {
                newsList.add(News.parse(el));
            }
            return newsList;
        }
        return null;
    }
}
