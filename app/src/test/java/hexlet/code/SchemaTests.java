package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaTests {
    private final Validator v = new Validator();
    private NumberSchema schema;
    private StringSchema schema1;

    @BeforeEach
    public final void beforeEach() {
        schema1 = v.string();
        schema = v.number();
    }

    @Test
    public void numberSchemaTest() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(2));
        assertFalse(schema.isValid("5"));

        schema.positive();
        assertTrue(schema.isValid(2));
        assertFalse(schema.isValid(-1));

        schema.range(0, 2);
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(2));
        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(-1));

    }

    @Test
    public void stringSchemaTest() {
        final int five = 5;

        assertTrue(schema.isValid(null));
        assertTrue(schema1.isValid(""));

        schema1.required();

        assertFalse(schema1.isValid(five));

        assertTrue(schema1.isValid("what does the fox say"));
        assertTrue(schema1.isValid("hexlet"));
        assertFalse(schema1.isValid(null));
        assertFalse(schema1.isValid(""));

        schema1.contains("wh");

        assertTrue(schema1.isValid("what does the fox say"));
        schema1.contains("what");
        assertTrue(schema1.isValid("what does the fox say"));
        schema1.contains("whatthe");
        assertFalse(schema1.isValid("what does the fox say"));

        assertFalse(schema1.isValid("what does the fox say"));

        schema1.minLength(2);
        assertTrue(schema1.isValid("whatthe"));
        schema1.minLength(five);
        assertFalse(schema1.isValid("wht"));

    }
}
