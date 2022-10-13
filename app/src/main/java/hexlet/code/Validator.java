package hexlet.code;

public final class Validator {
    public Validator() {
    }

    public StringSchema string() {
        return new StringSchema();
    }
    public NumberSchema number() {
        return new NumberSchema();
    }
}
