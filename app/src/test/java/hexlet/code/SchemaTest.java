package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class SchemaTest {
    private final int testNumber = 10;
    private final Validator v = new Validator();
    private final NumberSchema schemaNbr = v.number();

    private final StringSchema schemaStr = v.string();

    @BeforeEach
    public void beforeEach() {
        schemaStr.setStatus("not required");
        schemaNbr.setStatus("not required");
    }

    @Test
    public void stringSchemaTestRequired() {
        var actual = schemaStr.isValid("");
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestRequired2() {
        var actual = schemaStr.isValid(null);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestRequired3() {
        schemaStr.required();
        var actual = schemaStr.isValid(null);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void stringSchemaTestRequired4() {
        schemaStr.isValid("");
        schemaStr.required();
        var actual2 = schemaStr.isValid("test text");
        assertThat(actual2).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestContains() {
        var actual2 = schemaStr.contains("wh").isValid("what does the fox say");
        assertThat(actual2).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestContains2() {
        var actual2 = schemaStr.contains("what").isValid("what does the fox say");
        assertThat(actual2).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestContains3() {
        var actual2 = schemaStr.contains("whatthe").isValid("what does the fox say");
        assertThat(actual2).isEqualTo(false);
    }

    @Test
    public void stringSchemaTestContains4() {
        schemaStr.contains("wh").isValid("what does the fox say"); // true
        schemaStr.contains("what").isValid("what does the fox say"); // true
        schemaStr.contains("whatthe").isValid("what does the fox say"); // false
        schemaStr.isValid("what does the fox say");
        assertThat(schemaStr.isValid("what does the fox say")).isEqualTo(false);
    }

    @Test
    public void numberSchemaTestNull() {
        var actual = schemaNbr.isValid(null);
        assertThat(actual).isEqualTo(true);
    }

    @Test
    public void stringSchemaTestRequiredNull() {
        schemaNbr.required();
        var actual = schemaNbr.isValid(null);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void stringSchemaTestRequiredNumber() {
        assertThat(schemaNbr.isValid(null)).isEqualTo(true);
        schemaNbr.required();
        assertThat(schemaNbr.isValid(testNumber)).isEqualTo(true);
        assertThat(schemaNbr.isValid("5")).isEqualTo(false);
    }


}
