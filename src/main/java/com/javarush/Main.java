package com.javarush;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> ids = List.of(3, 254, 123, 4, 189, 89, 345, 118, 10);
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.processAndTestData(ids);
    }
}
