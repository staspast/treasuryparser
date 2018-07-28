package treasury.parser.invoice;

import treasury.parser.processor.file.ProcessedDbfFile;
import treasury.parser.processor.file.ProcessedTxtFile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceSorter {

    public List<Invoice> sortInvoices(List<ProcessedTxtFile> processedTxtFiles, ProcessedDbfFile processedDbfFile){
        List<Invoice> invoices = new ArrayList<>();
        for(ProcessedTxtFile processedFile : processedTxtFiles){
            invoices.addAll(processedFile.getInvoices());
        }
        List<String> orderedAccountList = processedDbfFile.getSortedAccounts();

        invoices.forEach(invoice -> {
            invoice.setSortIndex(orderedAccountList.indexOf(invoice.getAccount()));
        });

        return invoices.stream().sorted(Comparator.comparingInt(Invoice::getSortIndex)).collect(Collectors.toList());
    }
}
