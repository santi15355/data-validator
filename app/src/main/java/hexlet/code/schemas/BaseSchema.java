package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate<Object>> predicates = new HashMap<>();
    private static String status = "none";

    public final boolean isValid(Object obj) {

        if (predicates.isEmpty()) {
            return true;
        }
        if (status.equals("required") && obj == null || obj == "") {
            return false;
        }
        for (var predicate : predicates.entrySet()) {
            return predicate.getValue().test(obj);
        }
        return true;
    }

    public static String getStatus() {
        return status;
    }
    public static void setStatus(String value) {
        value = status;
    }

    public final void transferData(String flag, Predicate<Object> predicate) {
        status = flag;
        predicates.put(status, predicate);
    }
}
