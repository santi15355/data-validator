package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema {

    public NumberSchema required() {
        setRequiredOn();
        transferData(obj -> obj instanceof Integer);
        return this;
    }

    public NumberSchema positive() {
        transferData(obj -> obj instanceof Integer && (Integer) obj >= 0 || obj == null);
        return this;
    }

    public NumberSchema range(int min, int max) {
        transferData(obj -> (int) obj >= min && (int) obj <= max);
        return this;
    }
}
