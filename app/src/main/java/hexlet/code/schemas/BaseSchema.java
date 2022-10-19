package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema {

    private final List<Predicate<Object>> predicates = new ArrayList<>();
    private boolean required;

    public final boolean isValid(Object obj) {

        if (predicates.isEmpty()) {
            return true;
        } else if (required && obj == null || obj == "") {
            return false;
        } else {
            for (var predicate : predicates) {
                if (!predicate.test(obj)) {
                    return false;
                }
            }
        }
        return true;
    }

    protected final void transferData(Predicate<Object> predicate) {
        predicates.add(predicate);
    }

    protected final void setRequiredOn() {
        required = true;
    }
}
