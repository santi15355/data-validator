package hexlet.code;

public final class NumberSchema {
    private static String status = "not required";
    private static Integer number1;
    private static Integer number2;

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
        if (status.equals("range")) {
            return (int) obj >= number1 && (int) obj <= number2;
        }
        return false;
    }

    public void required() {
        status = "required";
    }

    public void positive() {
        setStatus("positive");
    }
    public void range(int data1, int data2) {
        setStatus("range");
        NumberSchema.number1 = data1;
        NumberSchema.number2 = data2;
    }
}
