package treasury.parser.processor;

import treasury.parser.config.regex.PatternProcessor;
import treasury.parser.invoice.Invoice;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TxtFileProcessor extends FileProcessor{

    @Override
    public void processFile(Path filePath) {
        List<String> fileLines = fileReader.readFile(filePath);
        List<Invoice> invoices = new ArrayList<>();

        for(int i = 0; i < fileLines.size(); i++){
            if(PatternProcessor.matchesPattern(fileLines.get(i))){
                int currentIndex = i;
                i = getNextNumericIndex(i);
                invoices.add(new Invoice(currentIndex - 4, i));
            }
        }
    }


    //TODO implement
    private int getNextNumericIndex(int index){
        return 0;
    }
}
