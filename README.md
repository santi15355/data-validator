### Hexlet tests and linter status:
[![Actions Status](https://github.com/santi15355/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/santi15355/java-project-78/actions)   [![build](https://github.com/santi15355/java-project-78/actions/workflows/build.yml/badge.svg)](https://github.com/santi15355/java-project-78/actions/workflows/build.yml)   [![Maintainability](https://api.codeclimate.com/v1/badges/066dfd9488503a1f1899/maintainability)](https://codeclimate.com/github/santi15355/java-project-78/maintainability)   [![Test Coverage](https://api.codeclimate.com/v1/badges/066dfd9488503a1f1899/test_coverage)](https://codeclimate.com/github/santi15355/java-project-78/test_coverage)

About validation:
Validation of strings:
required – любая непустая строка
minLength – строка равна или длиннее указанного числа
contains – строка содержит определённую подстроку
import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;

Validator v = new Validator();

StringSchema schema = v.string();

schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true
schema.isValid(null); // false
schema.isValid(""); // false

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// уже false, так как добавлена ещё одна проверка contains("whatthe")
Validation of numbers:
required – любое число включая ноль
positive – положительное число
range – диапазон, в который должны попадать числа включая границы
import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;

Validator v = new Validator();

NumberSchema schema = v.number();

schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(10) // true
schema.isValid("5"); // false

schema.positive().isValid(10); // true
schema.isValid(-10); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
Validation of Map type objects:
required – требуется тип данных Map
sizeof – количество пар ключ-значений в объекте Map должно быть равно заданному
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
Nested verification:
import hexlet.code.Validator;
import hexlet.code.schemas.MapSchema;

Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true