package ru.netology.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.notNullValue;

public class SchemaTest {
    @ParameterizedTest
    @MethodSource("addTestParameterized")
    public void convertTest(int a, char expected) {

        Schema schema = new Schema();

        char result = schema.convert(a);

        Assertions.assertEquals(expected, result);

    }
    public static Stream<Arguments> addTestParameterized() {
        return Stream.of(
                Arguments.of(0, '#'),
                Arguments.of(33, '$'),
                Arguments.of(65, '@'),
                Arguments.of(97, '%'),
                Arguments.of(130, '*'),
                Arguments.of(170, '+'),
                Arguments.of(210, '-'),
                Arguments.of(230, '\'')
        );

    }
    @Test
    public void givenString_whenNotNull_thenCorrect() {
        Schema schema = new Schema();

       char result = schema.convert(255);
        assertThat(result, notNullValue());
    }

}
