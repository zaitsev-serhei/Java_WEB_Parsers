package org.vavilon_learn.utils.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.vavilon_learn.Product;
import org.vavilon_learn.utils.JsoupPageParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebParser implements JsoupPageParser {
    private final String DEFAULT_URL = "https://webscraper.io/test-sites/e-commerce/allinone";
    @Override
    public List<Product> readPageFromURL(String url) {
        List<Product> productList = new ArrayList<>();
        if(url==null||url.isBlank()){
            url = DEFAULT_URL;
            System.out.println("URL is not provided. Using default one");
        }
        try {
            Document document = Jsoup.connect(url).userAgent("MyScraperBot/1.0").timeout(5000).get();
            String title = document.title();
            System.out.println(title);
            Elements products = document.select("div.card.thumbnail");
            for(Element el : products){
                String name = el.select("a[title]").text().trim();
                String priceText = el.select("span[itemprop=price]").text().replace("$","");
                Element ratingEl = el.selectFirst("diw.ratings p[data-rating]");
                double defaultRat = 0.0;
                if(ratingEl !=null){
                    defaultRat=Double.parseDouble(ratingEl.attr("data-rating"));
                } else {
                    defaultRat=(el.select("div.ratings .ws-icon-star").size());
                }
                float price = Float.parseFloat(priceText);
                Product prod = new Product();
                prod.setName(name);
                prod.setPrice(price);
                prod.setRating(defaultRat);
                productList.add(prod);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}
