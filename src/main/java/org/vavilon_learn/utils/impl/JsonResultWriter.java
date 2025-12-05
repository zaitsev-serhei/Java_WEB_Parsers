package org.vavilon_learn.utils.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.vavilon_learn.config.JsonMapperConfig;
import org.vavilon_learn.utils.ResultWriter;
import java.io.IOException;
import java.nio.file.*;

public class JsonResultWriter implements ResultWriter {
    private final ObjectMapper mapper = JsonMapperConfig.getMapper();

    @Override
    public Path writeResultToFile(Object result, String taskName) {
        Path dir = Paths.get("results");
        try {
            Files.createDirectories(dir); // concurrent safe
            //make sure to not use / in the regex as it fails file output
            String fileName = String.format("%s-%d-%d.json",
                    sanitizeFileName(taskName),
                    Thread.currentThread().getId(),
                    System.nanoTime());
            Path out = dir.resolve(fileName);
            Path tmp = Files.createTempFile(dir, taskName + "-", ".tmp");
            mapper.writerWithDefaultPrettyPrinter().writeValue(tmp.toFile(), result);
            try {
                Files.move(tmp, out, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException move) {
                System.out.println("Failed to move file because: " + move.getMessage());
                Files.move(tmp, out, StandardCopyOption.REPLACE_EXISTING);
            }
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Failed to write results in a file for task " + taskName + " " + e.getMessage(), e);
        }
    }

    private static String sanitizeFileName(String name) {
        // remove characters not allowed in filenames and trim length if necessary
        return name == null ? "task" : name.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
    }
}
