package treasury.parser.config.parameters;

public enum Parameter {

    CHCP("chcp"),
    PATH("path"),
    OUT("out"),
    SAL_DBF("sal_dbf"),
    EMAIL("email"),
    REM("rem"),
    EXTRACT("vypyska");

    private String value;

    Parameter(String s) {
        this.value = s;
    }

    @Override
    public String toString(){
        return this.value;
    }

    public static Parameter fromString(String text) {
        for (Parameter b : Parameter.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Вартість параметру " + text + " не знайдена");
    }
}
