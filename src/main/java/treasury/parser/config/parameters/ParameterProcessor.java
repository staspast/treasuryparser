package treasury.parser.config.parameters;

import treasury.parser.config.arguments.Argument;
import treasury.parser.config.arguments.ArgumentProcessor;
import treasury.parser.file.FileReader;

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

                    System.out.println("������� �������� " + parsedline[0] + " " + parsedline[1]);
                }
                catch (IllegalArgumentException e){
                    System.err.println("������� �������� ����� ������������ ��� �� " + line + " ����� ����� �� �����������");
                }
            }
//            else {
//                System.out.println("ǳ��������� ��� ����� ������������ " + line);
//            }
        });
    }

    public static String getParameter(Parameter parameter){
        return parameters.get(parameter);
    }
}
