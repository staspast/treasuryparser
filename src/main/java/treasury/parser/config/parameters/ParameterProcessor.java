package treasury.parser.config.parameters;

import treasury.parser.config.arguments.Argument;
import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.file.FileReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterProcessor {

    private static Map<Parameter, String> parameters;

    static {
        parameters = new HashMap<>();
    }

    public static void processParametersConfiguration(String path){
        FileReader fileReader = new FileReader();
        List<String> lines;
        path = ArgumentProcessor.getArgumentValue(Argument.CONFIGURATION_DIRECTORY) + FileReader.DIRECTORY_SEPARATOR + path;
        lines = fileReader.readFile(path);

        processConfigurationLines(lines);
    }

    private static void processConfigurationLines(List<String> lines){
        lines.forEach(line -> {
            if(!line.startsWith(Parameter.REM.toString()) && !line.isEmpty() && line.contains("=")){
                try {
                    String[] parsedline = line.split("=");
                    Parameter parameter = Parameter.fromString(parsedline[0].trim());
                    parameters.put(parameter, parsedline[1]);

                    System.out.println("Вчитано параметр " + parsedline[0] + " " + parsedline[1]);
                }
                catch (IllegalArgumentException e){
                    System.err.println("Помилка вчитання файлу конфігурації для лінії " + line + " назва змінної не підтримується");
                }
            }
        });
    }

    public static String getParameter(Parameter parameter){
        return parameters.get(parameter);
    }

    public static List<String> getExtractFileNames(){
        String[] extacts = parameters.get(Parameter.EXTRACT).split(",");
        List<String> fileNames = new ArrayList<>();
        for(int i = 0; i < extacts.length; i++){
            if(i == 0 || i % 2 == 0){
                fileNames.add(extacts[i]);
            }
        }

        return fileNames;
    }

    public static Integer getNumberOfLinesForTxtFile(String fileName){
        String[] extacts = parameters.get(Parameter.EXTRACT).split(",");
        for(int i = 0; i < extacts.length; i++){
            if(extacts[i].equals(fileName)){
                return Integer.parseInt(extacts[i+1]);
            }
        }

        return 0;
    }
}
