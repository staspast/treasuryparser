package treasury.parser.processor.factory;

import treasury.parser.processor.DbfFileProcessor;
import treasury.parser.processor.FileProcessor;
import treasury.parser.processor.TxtFileProcessor;

public class FileProcessorFactory {

    public FileProcessor getFileProcessor(String fileName){
        if(fileName.endsWith(".txt")){
            return new TxtFileProcessor();
        }
        if (fileName.endsWith(".dbf")){
            return new DbfFileProcessor();
        }

        throw new IllegalArgumentException("Не можна вчитати файл " + fileName);
    }
}
