package com.silviatanas.project.inventory;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Main {
    public void utility() {
        // setting folder paths
        String uncheckedFolderName = "Unchecked";
        String checkedFolderName = "Checked";
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        File uncheckedFolder = new File(uncheckedFolderName);
        Gson gson = new Gson();

        // validate if folder paths already exist
        if (uncheckedFolder.exists() && uncheckedFolder.isDirectory()) {
            for (File file : uncheckedFolder.listFiles()) {
                // convert json string to contract object
                String fileContent = null;
                try {
                    fileContent = Files.readString(uncheckedFolder.toPath());

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
            }

        } else { // create folders if they don't exist
            Paths.get(desktopPath, "Desktop", uncheckedFolderName);
        }
    }

    public static void main(String[] args) {
        // call util method
    }
}
