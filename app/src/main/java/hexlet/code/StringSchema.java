package hexlet.code;

public final class StringSchema extends BaseSchema {

    public void required() {
        this.transferData("required", obj -> obj instanceof String);
    }

    public void contains(String text) {
        transferData("contains", obj -> ((String) obj).contains(text));
    }

    public void minLength(int length) {
        transferData("minLength", obj -> ((String) obj).length() >= length);
    }
}
