package org.vavilon_learn;

import org.vavilon_learn.statistics.ProductStatistics;
import org.vavilon_learn.statistics.impl.ProductStatisticsImpl;
import org.vavilon_learn.utils.JsonFileReader;
import org.vavilon_learn.utils.JsoupPageParser;
import org.vavilon_learn.utils.ResultWriter;
import org.vavilon_learn.utils.impl.JsonFileReaderImpl;
import org.vavilon_learn.utils.impl.JsonResultWriter;
import org.vavilon_learn.utils.impl.WebParser;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {
    private static JsonFileReader jsonFileReader = new JsonFileReaderImpl();
    private static JsoupPageParser webParser = new WebParser();
    private static ResultWriter resultWriter = new JsonResultWriter();

    public static void main(String[] args) {
        String filePath = "C:\\Users\\User\\Desktop\\notes\\json_input.json";
        String filePath2 = "C:\\Users\\User\\IdeaProjects\\json_parser\\src\\main\\resources\\samples\\products.json";
        List<Product> productList = jsonFileReader.readProductsFromFileDefined(filePath2);

        Map<Long, Product> indexedProducts = productList.stream().collect(Collectors.toMap(Product::getId,
                p -> p,
                (a, b) -> a,
                ConcurrentHashMap::new));

        ProductStatistics statistics = new ProductStatisticsImpl(indexedProducts);
        /*
            For concurrency practice
            - creating an Execution Service with count of threads available or min 8
         */
        int threads = Math.min(8, Runtime.getRuntime().availableProcessors());
        System.out.println(threads);
        ExecutorService exService = Executors.newFixedThreadPool(threads);

        List<Callable<Path>> tasks = getCallables(statistics);

        try {
            List<Future<Path>> futures = exService.invokeAll(tasks);
            for (Future<Path> saved : futures) {
                Path file = saved.get();
                System.out.println("Results saved " + file);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Task failed " + e.getMessage());
        }

        exService.shutdown();
        try {
            if (!exService.awaitTermination(20, TimeUnit.SECONDS)) {
                exService.shutdown();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(webParser.readPageFromURL(""));
    }

    private static List<Callable<Path>> getCallables(ProductStatistics statistics) {
        List<Callable<Path>> tasks = new ArrayList<>();
        List<String> categories = List.of("electronics", "games");

        tasks.add(() -> {
            int count = statistics.countAllProducts();
            Map<String, Object> result = Map.of(
                    "task", "countAllProducts",
                    "count", count,
                    "timeStamp", Instant.now().toString()
            );
            return resultWriter.writeResultToFile(result, "countAllProducts");
        });

        tasks.add(() -> {
            List<Product> products = statistics.findAllProductsByCategories(categories);
            Map<String, Object> result = Map.of(
                    "task", "findProductsByCategory",
                    "categories", categories.toString(),
                    "products", products,
                    "timeStamp", Instant.now().toString()
            );
            return resultWriter.writeResultToFile(result, "findProductsByCategory");
        });

        tasks.add(() -> {
            Optional<Product> foundP = statistics.findProductById(1003L);
            Map<String, Object> result = Map.of(
                    "task", "findProductsById",
                    "toFind", 1003L,
                    "product", (foundP.isPresent() ? foundP.get() : "Product not found"),
                    "timeStamp", Instant.now().toString()
            );
            return resultWriter.writeResultToFile(result, "findProductsById");
        });

        tasks.add(() -> {
            String category = categories.get(0);
            Optional<Product> foundP = statistics.findMostSoldInCategory(category);
            Map<String, Object> result = Map.of(
                    "task", "findMostSoldInCategory",
                    "category", category,
                    "product", (foundP.isPresent() ? foundP.get() : "Product not found"),
                    "timeStamp", Instant.now().toString()
            );
            return resultWriter.writeResultToFile(result, "findMostSoldInCategory");
        });
        return tasks;
    }
}