package treasury.parser.config.arguments;

public enum Argument {

    CONFIGURATION_FILE("-conf"),
    CONFIGURATION_DIRECTORY("-cdir"),
    PATTERN_FILE("-patt"),
    EMAIL_CONFIG_FILE("-ecfg")
    ;

    private String value;

    Argument(String s){
        this.value = s;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Argument fromString(String text) {
        for (Argument b : Argument.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Вартість параметру " + text + " не знайдена");
    }
}
