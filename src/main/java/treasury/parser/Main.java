package treasury.parser;

import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.file.FileReader;

import java.io.IOException;

public class Main {

    public static void main(String[] args){

        try{
            ArgumentProcessor.processArguments(args);
        }
        catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }
}
