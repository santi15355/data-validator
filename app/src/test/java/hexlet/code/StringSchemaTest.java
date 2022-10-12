package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    @Test
    public void stringSchemaTest() {
    Validator v = new Validator();
    StringSchema schema = v.string();
    var actual = schema.isValid("");
    var expected = true;
    assertThat(actual).isEqualTo(expected);
    schema.required();
    assertThat(schema.isValid("")).isEqualTo(false);
    }
}
