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
        int accountIndex = 0;
        int nextInvoiceIndex = 0;
        for(int i = 0; i < fileLines.size(); i++){
            if((PatternProcessor.hasAccountInLine(fileLines.get(i)) && PatternProcessor.hasKeyWordInLine(fileLines.get(i))) || i == fileLines.size() - 1){
                nextInvoiceIndex = i - 4;
            }

            if(accountIndex != 0 && nextInvoiceIndex != 0 && accountIndex != nextInvoiceIndex){
                invoices.add(new Invoice(accountIndex, nextInvoiceIndex, fileLines.get(i)));
                accountIndex = 0;
                nextInvoiceIndex = 0;
            }

            if(PatternProcessor.matchesPattern(fileLines.get(i)) && PatternProcessor.hasKeyWordInLine(fileLines.get(i))){
                accountIndex = i - 4;
            }
        }

        invoices.forEach(invoice -> invoice.setText(fileLines));
        invoices.forEach(System.out::println);
    }


    //TODO implement
    private int getNextNumericIndex(int index){
        return 0;
    }
}
