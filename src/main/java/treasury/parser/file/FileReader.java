package treasury.parser.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {

    public static final String DIRECTORY_SEPARATOR = "\\";

    public List<String> readFile(String filePath) {
        return this.readFile(Paths.get(filePath));
    }

    public List<String> readFile(Path filePath) {
        try {
            return Files.lines(filePath, Charset.forName("windows-1251")).collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Не вдалось прочитати файл конфігурації за шляхом " + filePath);
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Path> listFilesInDirectory(String path) throws IOException {

        return Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }
}
