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

    public MapSchema shape(Map<String, BaseSchema> map) {
        transferData("shape", obj -> {
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) obj).entrySet()) {
                if (!map.get(entry.getKey()).isValid(entry.getValue())) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
