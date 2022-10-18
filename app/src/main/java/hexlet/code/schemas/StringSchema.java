package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        transferData("required", obj -> obj instanceof String);
        return this;
    }

    public StringSchema contains(String text) {
        transferData("contains", obj -> ((String) obj).contains(text));
        return this;
    }

    public StringSchema minLength(int value) {
        transferData("minLength", obj -> obj.toString().length() >= value);
        return this;
    }
}
