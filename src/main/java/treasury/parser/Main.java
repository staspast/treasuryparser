package treasury.parser;

import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.config.parameters.Parameter;
import treasury.parser.config.parameters.ParameterProcessor;
import treasury.parser.config.regex.PatternProcessor;
import treasury.parser.export.FileExporter;
import treasury.parser.file.FileReader;
import treasury.parser.processor.FileProcessor;
import treasury.parser.processor.factory.FileProcessorFactory;
import treasury.parser.processor.file.ProcessedDbfFile;
import treasury.parser.processor.file.ProcessedFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        try{
            ArgumentProcessor.processArguments(args);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        FileExporter fileExporter = new FileExporter();
        fileExporter.exportProcessedFiles();
    }
}
