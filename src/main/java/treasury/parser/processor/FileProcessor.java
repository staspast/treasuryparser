package treasury.parser.processor;

import treasury.parser.file.FileReader;
import treasury.parser.processor.file.ProcessedFile;

import java.nio.file.Path;

public abstract class FileProcessor {

    protected FileReader fileReader;

    public FileProcessor(){
        fileReader = new FileReader();
    }

    public abstract ProcessedFile processFile(Path filePath);
}
