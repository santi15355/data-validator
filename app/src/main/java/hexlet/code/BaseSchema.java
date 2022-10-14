package hexlet.code;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate<Object>> predicates = new HashMap<>();
    private static String status = "none";

    public final boolean isValid(Object obj) {

        if (status.equals("none")) {
            return true;
        }
        if (status.equals("required") && obj == null || obj == "") {
            return false;
        }
        if (predicates.isEmpty()) {
            return true;
        }
        for (var predicate : predicates.entrySet()) {
            return predicate.getValue().test(obj);
        }
        return status.equals("none");

    }

    public static String getStatus() {
        return status;
    }

    public final void transferData(String flag, Predicate<Object> predicate) {
        status = flag;
        predicates.put(status, predicate);
    }
}
