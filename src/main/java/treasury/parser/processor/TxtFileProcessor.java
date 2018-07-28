package treasury.parser.processor;

import treasury.parser.config.parameters.ParameterProcessor;
import treasury.parser.config.regex.PatternProcessor;
import treasury.parser.invoice.Invoice;
import treasury.parser.processor.file.ProcessedFile;
import treasury.parser.processor.file.ProcessedTxtFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TxtFileProcessor extends FileProcessor{

    @Override
    public ProcessedFile processFile(Path filePath) {
        List<String> fileLines = fileReader.readFile(filePath);
        Integer numberOfLinesToSubtract = ParameterProcessor.getNumberOfLinesForTxtFile(filePath.getFileName().toString());
        List<Invoice> invoices = new ArrayList<>();
        int accountIndex = 0;
        int nextInvoiceIndex = 0;
        for(int i = 0; i < fileLines.size(); i++){
            if((PatternProcessor.hasAccountInLine(fileLines.get(i)) && PatternProcessor.hasKeyWordInLine(fileLines.get(i))) || i == fileLines.size() - 1){
                nextInvoiceIndex = i - numberOfLinesToSubtract;
            }

            if(accountIndex != 0 && nextInvoiceIndex != 0 && accountIndex != nextInvoiceIndex){
                invoices.add(new Invoice(accountIndex, nextInvoiceIndex, PatternProcessor.getAccountNumberFromLine(fileLines.get(i))));
                accountIndex = 0;
                nextInvoiceIndex = 0;
            }

            if(PatternProcessor.matchesPattern(fileLines.get(i)) && PatternProcessor.hasKeyWordInLine(fileLines.get(i))){
                accountIndex = i - numberOfLinesToSubtract;
            }
        }

        invoices.forEach(invoice -> invoice.setText(fileLines));
        return new ProcessedTxtFile(invoices);
    }
}
