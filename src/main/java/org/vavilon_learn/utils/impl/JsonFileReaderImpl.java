package org.vavilon_learn.utils.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.vavilon_learn.Product;
import org.vavilon_learn.utils.JsonFileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JsonFileReaderImpl implements JsonFileReader {
    private final ObjectMapper mapper;
    {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public List<Product> readProductsFromFileDefined(String path) {
        if (path.isEmpty()) {
            throw new IllegalArgumentException("Path is missing");
        }
        Path filePath = Path.of(path);
        CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, Product.class);
        try {
            return mapper.readValue(filePath.toFile(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
