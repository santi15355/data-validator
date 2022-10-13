package hexlet.code;

public final class NumberSchema {
    private static String status = "not required";
    private static String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String flag) {
        NumberSchema.status = flag;
    }

    public boolean isValid(Object obj) {
        if (status.equals("not required")) {
            return true;
        }
        if (status.equals("required") && obj instanceof Integer) {
            return true;
        }
        if (status.equals("positive")) {
            return ((int) obj) >= 0;
        }
        return false;
    }

    public void required() {
        status = "required";
    }

    public void positive() {
        setStatus("positive");
    }
}
