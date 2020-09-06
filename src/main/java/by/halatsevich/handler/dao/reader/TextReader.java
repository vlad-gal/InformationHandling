package by.halatsevich.handler.dao.reader;

import by.halatsevich.handler.dao.exception.DaoException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {
    private static final String PROPERTIES = "config";
    private static final String FILE_PATH = "filePath";
    private static final String REGEX_NEW_LINE = "\n";

    public String readText() throws DaoException {
        ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES);
        String fileName = bundle.getString(FILE_PATH);
        URL url = getClass().getClassLoader().getResource(fileName);
        File file;
        if (url != null) {
            file = new File(url.getFile());
        } else {
            throw new DaoException("File not exist");
        }
        Path path = Paths.get(file.getPath());
        List<String> lines;
        try (Stream<String> partOfText = Files.lines(path)) {
            lines = partOfText.filter(part -> !part.isEmpty())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new DaoException("Error while reading file", e);
        }
        StringBuilder text = new StringBuilder();
        for (String line : lines) {
            text.append(line).append(REGEX_NEW_LINE);
        }
        return text.toString();
    }
}
