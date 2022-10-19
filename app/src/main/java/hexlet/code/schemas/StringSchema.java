package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        setRequiredOn();
        transferData(obj -> obj instanceof String);
        return this;
    }

    public StringSchema contains(String text) {
        transferData(obj -> ((String) obj).contains(text));
        return this;
    }

    public StringSchema minLength(int value) {
        transferData(obj -> obj.toString().length() >= value);
        return this;
    }
}
