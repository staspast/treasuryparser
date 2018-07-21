package treasury.parser.processor;

import treasury.parser.file.FileReader;

import java.nio.file.Path;

public abstract class FileProcessor {

    protected FileReader fileReader;

    public FileProcessor(){
        fileReader = new FileReader();
    }

    public abstract void processFile(Path filePath);
}
