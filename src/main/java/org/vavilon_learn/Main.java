package org.vavilon_learn;

import org.vavilon_learn.statistics.ProductStatistics;
import org.vavilon_learn.statistics.impl.ProductStatisticsImpl;
import org.vavilon_learn.utils.JsonFileReader;
import org.vavilon_learn.utils.JsoupPageParser;
import org.vavilon_learn.utils.impl.JsonFileReaderImpl;
import org.vavilon_learn.utils.impl.WebParser;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static JsonFileReader jsonFileReader = new JsonFileReaderImpl();
    private static JsoupPageParser webParser = new WebParser();

    public static void main(String[] args) {
        String filePath = "C:\\Users\\User\\Desktop\\notes\\json_input.json";
        String filePath2 = "C:\\Users\\User\\Desktop\\notes\\products.json";
        List<Product> productList = jsonFileReader.readProductsFromFileDefined(filePath2);
//        System.out.println(productList);
        ProductStatistics statistics = new ProductStatisticsImpl(productList);
        //System.out.println(statistics.findMostSoldInCategory("games"));
        List<String> categories = List.of("electronics","games");
        //System.out.println(statistics.findAllProductsByCategories(categories));
        System.out.println(statistics.countAllProducts());
        System.out.println(webParser.readPageFromURL(""));
    }
}