package org.vavilon_learn.statistics;

import org.vavilon_learn.Product;
import java.util.List;
import java.util.Optional;

public interface ProductStatistics {
    int countAllProducts();
    Optional<Product> findProductById(Long productId);
    List<Product> findAllProductsByCategories(List<String> categories);
    Optional<Product> findMostSoldInCategory(String category);
}
