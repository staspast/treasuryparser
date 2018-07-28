package treasury.parser.export;

import treasury.parser.config.parameters.Parameter;
import treasury.parser.config.parameters.ParameterProcessor;
import treasury.parser.file.FileReader;
import treasury.parser.invoice.Invoice;
import treasury.parser.invoice.InvoiceSorter;
import treasury.parser.processor.FileProcessor;
import treasury.parser.processor.factory.FileProcessorFactory;
import treasury.parser.processor.file.ProcessedDbfFile;
import treasury.parser.processor.file.ProcessedFile;
import treasury.parser.processor.file.ProcessedTxtFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileExporter {

    private FileReader fileReader;
    private InvoiceSorter invoiceSorter;

    public FileExporter(){
        fileReader = new FileReader();
        invoiceSorter = new InvoiceSorter();
    }

    @SuppressWarnings("unchecked")
    public void exportProcessedFiles(){

        List<ProcessedFile> processedFiles = getProcessedFiles();
        ProcessedFile processedDbfFile = null;
        for(ProcessedFile processedFile : processedFiles) {
            if (processedFile instanceof ProcessedDbfFile) {
                processedDbfFile = processedFile;
            }
        }
        if(processedDbfFile != null){
            processedFiles.remove(processedDbfFile);
        }
        List<Invoice> sortedInvoices = invoiceSorter.sortInvoices((List<ProcessedTxtFile>)(Object) processedFiles, (ProcessedDbfFile) processedDbfFile);
        List<String> invoiceTextList = sortedInvoices.stream().map(Invoice::getOutputText).collect(Collectors.toList());

        try {
            Files.write(Paths.get(ParameterProcessor.getParameter(Parameter.OUT) + "\\out.txt"), invoiceTextList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<ProcessedFile> getProcessedFiles(){
        FileProcessorFactory fileProcessorFactory = new FileProcessorFactory();

        List<Path> files = fileReader.listFilesInDirectory(ParameterProcessor.getParameter(Parameter.PATH));
        List<ProcessedFile> processedFiles = new ArrayList<>();
        files.forEach(file -> {
            if(ParameterProcessor.getExtractFileNames().contains(file.getFileName().toString())
                    || file.getFileName().toString().equalsIgnoreCase(ParameterProcessor.getParameter(Parameter.SAL_DBF))) {
                FileProcessor fileProcessor = fileProcessorFactory.getFileProcessor(file.getFileName().toString());
                processedFiles.add(fileProcessor.processFile(file));
            }
        });

        return processedFiles;
    }
}
