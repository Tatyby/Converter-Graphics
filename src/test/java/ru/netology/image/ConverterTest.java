package ru.netology.image;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConverterTest {
    @Test
    public void stringBilderTest() {
        Converter converter = new Converter();
        char [][] chars = {
                {'#', '$'},
                {'-', '+'}
        };
        String expected ="\n" + "##$$" + "\n" + "--++";
        String result = converter.stringBilder(chars);
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void givenString_whenNotNull_thenCorrect() {
        Converter converter = new Converter();
        char [][] chars = {
                {'#', '$'},
                {'-', '+'}
        };

        String result = converter.stringBilder(chars);
        assertThat(result, notNullValue());         //не является ли строка нулевой
    }



    //@ParameterizedTest
//    @MethodSource("addTestParameterized")
//    public void charConvertTest(int[][] ints, char[][] expected){
//        Converter converter = new Converter();
//
//        char [][] result = converter.charConvert();
//
//    }
//    //{'#', '$', '@', '%', '*', '+', '-', '\''};
//    public static Stream<Arguments> addTestParameterized() {
//        return Stream.of(Arguments.of((new int[][]{{1, 0, 200}, {20,70,150}},
//                        new char[][]{{'#', '#', '-'}, {'#', '$', '*'}});
//
//
//}
}
