package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaTests {
    private final Validator v = new Validator();
    private NumberSchema nbrSchema;
    private StringSchema strSchema;

    @BeforeEach
    public final void beforeEach() {
        strSchema = v.string();
        nbrSchema = v.number();
    }

    @Test
    public void numberSchemaTest() {
        assertTrue(nbrSchema.isValid(null));

        nbrSchema.required();

        assertFalse(nbrSchema.isValid(null));
        assertTrue(nbrSchema.isValid(2));
        assertFalse(nbrSchema.isValid("5"));

        assertTrue(nbrSchema.positive().isValid(2));
        assertFalse(nbrSchema.isValid(-1));

        nbrSchema.range(0, 2);
        assertTrue(nbrSchema.isValid(0));
        assertTrue(nbrSchema.isValid(2));
        assertFalse(nbrSchema.isValid(-1));
        assertFalse(nbrSchema.isValid(-1));

    }

    @Test
    public void stringSchemaTest() {
        final int five = 5;

        assertTrue(nbrSchema.isValid(null));
        assertTrue(strSchema.isValid(""));

        strSchema.required();

        assertFalse(strSchema.isValid(five));

        assertTrue(strSchema.isValid("what does the fox say"));
        assertTrue(strSchema.isValid("hexlet"));
        assertFalse(strSchema.isValid(null));
        assertFalse(strSchema.isValid(""));

        assertTrue(strSchema.contains("wh")
                .isValid("what does the fox say"));
        assertTrue(strSchema.contains("what")
                .isValid("what does the fox say"));
        assertFalse(strSchema.contains("whatthe")
                .isValid("what does the fox say"));

        strSchema.minLength(2);
        assertTrue(strSchema.isValid("whatthe"));
        strSchema.minLength(five);
        assertFalse(strSchema.isValid("wht"));

    }
}
