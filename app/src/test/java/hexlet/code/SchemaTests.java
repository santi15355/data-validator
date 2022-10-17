package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class SchemaTests {
    private final Validator v = new Validator();
    private NumberSchema nbrSchema;
    private StringSchema strSchema;
    private MapSchema mapSchema;

    @BeforeEach
    public void beforeEach() {
        strSchema = v.string();
        nbrSchema = v.number();
        mapSchema = v.map();
    }

    @Test
    public void stringSchemaTest() {
        final int five = 5;
        assertTrue(strSchema.isValid(""));
        assertTrue(strSchema.isValid("null"));

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
    public void mapSchemaTest() {

        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(""));

        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new HashMap<>()));
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(mapSchema.isValid(data));

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(data));
        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));
    }
}
