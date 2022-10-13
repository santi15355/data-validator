package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public final class SchemaTest {
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
    public void numberSchemaTestRequiredNull() {
        schemaNbr.required();
        var actual = schemaNbr.isValid(null);
        assertThat(actual).isEqualTo(false);
    }

    @Test
    public void numberSchemaTestRequiredNumber() {
        assertThat(schemaNbr.isValid(null)).isEqualTo(true);
        schemaNbr.required();
        final int testNumber = 10;
        assertThat(schemaNbr.isValid(testNumber)).isEqualTo(true);
        assertThat(schemaNbr.isValid("5")).isEqualTo(false);
    }

    @Test
    public void numberSchemaTestPositive() {
        schemaNbr.positive();
        final int testNumber = -10;
        assertThat(schemaNbr.isValid(testNumber)).isEqualTo(false);
    }

    @Test
    public void numberSchemaTestRange() {
        final int four = 4;
        final int five = 5;
        final int seven = 7;
        final int nine = 9;
        schemaNbr.range(five, nine);
        assertThat(schemaNbr.isValid(four)).isEqualTo(false);
        assertThat(schemaNbr.isValid(five)).isEqualTo(true);
        assertThat(schemaNbr.isValid(seven)).isEqualTo(true);
        assertThat(schemaNbr.isValid(nine)).isEqualTo(true);
    }
}
