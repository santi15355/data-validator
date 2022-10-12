package hexlet.code;

public final class StringSchema {
    private static String status = "not required";

    public StringSchema() {
    }

    public static void setStatus() {
        status = status;
    }

    public static String getStatus() {
        return status;
    }

    public boolean isValid(Object obj) {
        if (status.equals("not required")) {
            return true;
        }
        return false;
    }

    public void required() {
        status = "required";
    }

    public void contains() {
        status = "contains";

    }
}
