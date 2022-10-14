package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class SchemaTest {
    private final Validator v = new Validator();
    private StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        schema = v.string();
    }

    @Test
    public void stringSchemaTest() {
        System.out.println(BaseSchema.getStatus());
        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false

        schema.contains("wh");

        assertTrue(schema.isValid("what does the fox say")); // true
        schema.contains("what");
        assertTrue(schema.isValid("what does the fox say")); // true
        schema.contains("whatthe");
        assertFalse(schema.isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false
    }
}
