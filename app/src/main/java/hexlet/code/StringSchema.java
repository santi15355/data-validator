package hexlet.code;

public final class StringSchema {
    private static String status = "not required";
    private static String data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String flag) {
        StringSchema.status = flag;
    }

    public boolean isValid(Object obj) {
        if (status.equals("not required")) {
            return true;
        }
        if (status.equals("required") && obj == null || obj == "") {
            return false;
        }
        if (status.equals("contains")) {
            return ((String) obj).contains(data);
        }
        return true;
    }

    public void required() {
        status = "required";
    }

    public StringSchema contains(String text) {
        status = "contains";
        StringSchema.data = text;
        return this;
    }
}
