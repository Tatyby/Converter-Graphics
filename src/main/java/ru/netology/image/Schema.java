package ru.netology.image;

public class Schema implements TextColorSchema {

    char[] ch = new char[]{'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {

        return ch[color / 32];
    }
}
