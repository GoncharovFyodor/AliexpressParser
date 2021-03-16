package ru.goncharov.aliexpressparser;

import ru.goncharov.aliexpressparser.parser.AliexpressParser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AliexpressParser aliexpressParser= new AliexpressParser();
        aliexpressParser.massParse(9);
    }
}
