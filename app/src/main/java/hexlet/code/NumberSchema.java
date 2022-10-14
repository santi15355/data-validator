package hexlet.code;

public final class NumberSchema extends BaseSchema {

    public void required() {
        this.transferData("required", obj -> obj instanceof Integer);
    }

    public void positive() {
        transferData("positive", obj -> (int) obj >= 0);
    }

    public void range(int min, int max) {
        transferData("range", obj -> (int) obj >= min && (int) obj <= max);
    }
}
