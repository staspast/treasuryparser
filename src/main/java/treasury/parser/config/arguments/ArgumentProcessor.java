package treasury.parser.config.arguments;

import treasury.parser.config.parameters.ParameterProcessor;
import treasury.parser.config.regex.PatternProcessor;

import java.util.HashMap;
import java.util.Map;

public class ArgumentProcessor {

    private static Map<Argument, String> arguments;

    static {
        arguments = new HashMap<>();
        arguments.put(Argument.CONFIGURATION_FILE, "vip_bars.ini");
        arguments.put(Argument.PATTERN_FILE, "acctptrn.txt");
        arguments.put(Argument.EMAIL_CONFIG_FILE, "MyCfg.ini");
        arguments.put(Argument.CONFIGURATION_DIRECTORY, ".\\config");
    }

    public static void processArguments(String[] args) throws IllegalArgumentException{

        for(int i = 0; i < args.length; i += 2){
            if(!arguments.containsKey(Argument.fromString(args[i]))){
                throw new IllegalArgumentException("Невідомий параметр запуску " + args[i]);
            }
            try {
                arguments.replace(Argument.fromString(args[i]), args[i+1]);
            }
            catch (IndexOutOfBoundsException e){
                throw new IllegalArgumentException("Вартість параметру " + args[i] + " є порожньою");
            }
        }

        ParameterProcessor.processParametersConfiguration(getArgumentValue(Argument.CONFIGURATION_FILE));
        PatternProcessor.processRegexConfiguration(getArgumentValue(Argument.PATTERN_FILE));
    }

    public static String getArgumentValue(Argument argument){
        return arguments.get(argument);
    }
}
