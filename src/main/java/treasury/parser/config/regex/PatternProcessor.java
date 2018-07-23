package treasury.parser.config.regex;

import treasury.parser.config.arguments.Argument;
import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.file.FileReader;

import java.util.ArrayList;
import java.util.List;

public class PatternProcessor {

    private static List<String> patternList;

    static {
        patternList = new ArrayList<>();
    }

    public static void processRegexConfiguration(String path){
        FileReader fileReader = new FileReader();
        path = ArgumentProcessor.getArgumentValue(Argument.CONFIGURATION_DIRECTORY) + FileReader.DIRECTORY_SEPARATOR + path;
        patternList = fileReader.readFile(path);
    }

    public static boolean matchesPattern(String line){
        String number = getAccountNumberFromLine(line);

        if(number != null){
            for(String pattern : patternList){
                if(number.matches(pattern)){
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean hasKeyWordInLine(String line){
        return line.contains("Рахунок:");
    }

    public static boolean hasAccountInLine(String line){
        return getAccountNumberFromLine(line) != null;
    }

    public static String getAccountNumberFromLine(String line){
        String[] splitLine = line.split(" ");
        String number = null;

        for(String s : splitLine){
            if(s.matches("\\d{14}")){
                number = s;
                break;
            }
        }

        return number;
    }
}
