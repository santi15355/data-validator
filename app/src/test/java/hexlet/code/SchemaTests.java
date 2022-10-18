package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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

        strSchema.minLength(five);

        assertTrue(strSchema.isValid("whatthe"));
        assertFalse(strSchema.isValid("wht"));
        assertTrue(strSchema.isValid("whafe"));

        assertTrue(strSchema.contains("wh")
                .isValid("what does the fox say"));
        assertTrue(strSchema.contains("what")
                .isValid("what does the fox say"));
        assertFalse(strSchema.contains("whatthe")
                .isValid("what does the fox say"));
    }

    @Test
    public void numberSchemaTest() {
        final int five = 5;

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
        assertTrue(nbrSchema.isValid(1));
        assertFalse(nbrSchema.isValid(-1));
        assertFalse(nbrSchema.isValid(five));

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

    @Test
    public void shapeTest() {
        final int negNumber = -5;
        final int posNumber = 100;

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", null);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", posNumber);
        assertTrue(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", negNumber);
        assertFalse(mapSchema.isValid(human4));
    }
}
