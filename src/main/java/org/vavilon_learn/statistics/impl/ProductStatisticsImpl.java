package org.vavilon_learn.statistics.impl;

import org.vavilon_learn.Product;
import org.vavilon_learn.statistics.ProductStatistics;
import java.util.*;
import java.util.stream.Collectors;

public class ProductStatisticsImpl implements ProductStatistics {
    private Map<Long, Product> indexedProducts;

    public ProductStatisticsImpl(Map<Long, Product> productInfo) {
        this.indexedProducts = productInfo;
    }

    @Override
    public int countAllProducts() {
        if (indexedProducts.isEmpty()) {
            throw new IllegalArgumentException("Products are missing. Nothing to analyze");
        }
        return indexedProducts.size();
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        return Optional.ofNullable(indexedProducts.get(productId));
    }

    @Override
    public List<Product> findAllProductsByCategories(List<String> categories) {
        if (categories.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> categorySet = new HashSet<>(categories);
        return indexedProducts.values().stream().filter(p ->
                categorySet.contains(p.getCategory())
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findMostSoldInCategory(String category) {
        if (category == null || category.isBlank()) {
            return Optional.empty();
        }
        return indexedProducts.values().stream().filter(p ->
                p.getCategory().equals(category)
        ).max(Comparator.comparingInt(Product::getSold));
    }
}
