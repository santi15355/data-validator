package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberTest {
    private final Validator v = new Validator();
    private NumberSchema schema;

    @BeforeEach
    public final void beforeEach() {
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
}
