package com.silviatanas.project.inventory;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public static void utility(File uncheckedFolder, File checkedFolder) {
        Gson gson = new Gson();

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("csv"));

            // validate if folder paths already exist
            for (File file : uncheckedFolder.listFiles()) {
                if (file.getAbsolutePath().endsWith(".json")) {
                    // convert json string to contract object
                    String fileContent = null;
                    try {
                        fileContent = Files.readString(file.toPath());

                        Contract contract = gson.fromJson(fileContent, Contract.class);

                        // move processed files
                        if (checkedFolder.exists() && checkedFolder.isDirectory()) {
                            Files.move(Paths.get(file.getAbsolutePath()), Paths.get(checkedFolder.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                        } else {
                            checkedFolder.mkdir();
                            Files.move(Paths.get(file.getAbsolutePath()), Paths.get(checkedFolder.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
                        }

                        // data written into csv
                        writer.write(contract.toString());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing into CSV");
        }
    }

    public static void main(String[] args) {
        // setting folder paths
        String downloadPath = System.getProperty("user.home") + File.separator + "Download";
        File uncheckedFolder = new File(downloadPath + File.separator + "Unchecked");
        File checkedFolder = new File(downloadPath + File.separator + "Checked");
        System.out.println("1");
        System.out.println(uncheckedFolder);

        if (uncheckedFolder.exists() && uncheckedFolder.isDirectory()) {
            System.out.println("2");
            utility(uncheckedFolder, checkedFolder);
        } else { // unchecked doesn't exist so there's no actions to be taken
            System.out.println("3");
            System.exit(0);
        }
    }
}
