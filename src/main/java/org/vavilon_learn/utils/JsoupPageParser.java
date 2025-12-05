package org.vavilon_learn.utils;

import org.vavilon_learn.Product;
import java.util.List;

public interface JsoupPageParser {
    List<Product> readPageFromURL(String url);
}
