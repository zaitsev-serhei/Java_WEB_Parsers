package org.vavilon_learn.utils;

import java.nio.file.Path;

public interface ResultWriter {
    Path writeResultToFile(Object result, String taskName);
}
