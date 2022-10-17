package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        transferData("required", obj -> obj instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        transferData("positive", obj -> obj instanceof Integer && (Integer) obj >= 0 || obj == null);
        return this;
    }

    public NumberSchema range(int min, int max) {
        transferData("range", obj -> (int) obj >= min && (int) obj <= max);
        return this;
    }
}
