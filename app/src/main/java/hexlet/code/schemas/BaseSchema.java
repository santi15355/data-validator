package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final Map<String, Predicate<Object>> predicates = new HashMap<>();
    private static String status;
    private boolean flag = false;

    public final boolean isValid(Object obj) {
        boolean result = !flag;

        if (predicates.isEmpty()) {
            result = true;
        } else if (status.equals("required") && obj == null || obj == "") {
            result = false;
            //return flag;
        } else {
            for (var predicate : predicates.entrySet()) {
                return predicate.getValue().test(obj);
            }
        }
        return result;
    }

    /*public static String getStatus() {
        return status;
    }*/
    /*public static void setStatus(String value) {
        value = status;
    }*/

    public final void transferData(String flag, Predicate<Object> predicate) {
        status = flag;
        predicates.put(status, predicate);
    }
}
