package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    public MapSchema required() {
        transferData("required", obj -> obj instanceof Map);
        return this;
    }

    public MapSchema sizeof(int value) {
        transferData("sizeof", obj -> ((Map<?, ?>) obj).size() == value);
        return this;
    }
}
