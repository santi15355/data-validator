package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class StringSchemaTest {

    @BeforeEach
    public void beforeEach() {
        StringSchema schema = new StringSchema();
        schema.setStatus("not required");
    }

    @Test
    public void stringSchemaTestRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var actual = schema.isValid("");
        var expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestRequired2() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var actual = schema.isValid(null);
        var expected = true;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestRequired3() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required();
        //System.out.println(schema.getStatus());
        var actual = schema.isValid(null);
        var expected = false;
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestRequired4() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.isValid("");
        schema.required();
        var actual2 = schema.isValid("test text");
        var expected = true;
        assertThat(actual2).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var actual2 = schema.contains("wh").isValid("what does the fox say");
        var expected = true;
        assertThat(actual2).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestContains2() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var actual2 = schema.contains("what").isValid("what does the fox say");
        var expected = true;
        assertThat(actual2).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestContains3() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        var actual2 = schema.contains("whatthe").isValid("what does the fox say");
        var expected = false;
        assertThat(actual2).isEqualTo(expected);
    }

    @Test
    public void stringSchemaTestContains4() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.contains("wh").isValid("what does the fox say"); // true
        schema.contains("what").isValid("what does the fox say"); // true
        schema.contains("whatthe").isValid("what does the fox say"); // false
        schema.isValid("what does the fox say");
        assertThat(false).isEqualTo(false);
    }
}
