package treasury.parser;

import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.config.parameters.Parameter;
import treasury.parser.config.parameters.ParameterProcessor;
import treasury.parser.config.regex.PatternProcessor;
import treasury.parser.file.FileReader;
import treasury.parser.processor.FileProcessor;
import treasury.parser.processor.factory.FileProcessorFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args){

        try{
            ArgumentProcessor.processArguments(args);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        FileProcessorFactory fileProcessorFactory = new FileProcessorFactory();

        FileReader fileReader = new FileReader();

        List<Path> files = fileReader.listFilesInDirectory(ParameterProcessor.getParameter(Parameter.PATH));

        files.forEach(file -> {
            FileProcessor fileProcessor = fileProcessorFactory.getFileProcessor(file.getFileName().toString());
            fileProcessor.processFile(file);
        });
    }
}
