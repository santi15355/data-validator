package hexlet.code;

public class StringSchema {
    public String status = "not required";
    public StringSchema() {

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
