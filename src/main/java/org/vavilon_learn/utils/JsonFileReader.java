package org.vavilon_learn.utils;

import org.vavilon_learn.Product;
import java.nio.file.Path;
import java.util.List;

public interface JsonFileReader {
    List<Product> readProductsFromFileDefined(String path);
}
