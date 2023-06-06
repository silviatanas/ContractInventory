package com.silviatanas.project.inventory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();

        File file = new File("test.csv");
        try {
            BufferedReader data = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
