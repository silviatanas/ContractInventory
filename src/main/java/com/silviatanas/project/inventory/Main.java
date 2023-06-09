package com.silviatanas.project.inventory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public void utility() {
        File folder = new File("/path/to/folder");
        Gson gson = new Gson();

        for (File file : folder.listFiles()) {
            if (folder.isDirectory()) { // validate directory?
                try {
                    // convert json string to contract object
                    String fileContent = Files.readString(folder.toPath());
                    Contract contract = gson.fromJson(fileContent, Contract.class);

                    // move processed files
                    Files.move(Paths.get("/currentFile"), Paths.get("/targetFolder"), StandardCopyOption.REPLACE_EXISTING);

                    // data written into csv
                    BufferedWriter writer = new BufferedWriter(new FileWriter("/csvPath"));
                    writer.write(contract.toString());
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // fail directory check?
            }
        }
    }

    public static void main(String[] args) {
        // call util method
    }
}
