package com.sidorovich.pavel.soap.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Library {

    private static final String FILE_NAME =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork6\\server\\src\\main\\resources\\books.txt";
    private static final String SEPARATOR = ":";

    public String[] findByBookName(String name) {
        return findData(name, 0);
    }

    public String[] findByAuthor(String author) {
        return findData(author, 1);
    }

    private String[] findData(String searchString, int posIndex) {
        final String[] found = new String[40];
        int foundItems = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] strings = line.split(SEPARATOR);
                if (strings[posIndex].contains(searchString)) {
                    found[foundItems++] = strings[posIndex ^ 1];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return found;
    }

}
