package ru.netology;


import ru.netology.image.Converter;
import ru.netology.image.TextGraphicsConverter;
import ru.netology.server.GServer;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter = new Converter();

        GServer server = new GServer(converter);
        server.start();


    }
}