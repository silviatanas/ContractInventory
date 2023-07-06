package com.silviatanas.project.inventory;

import java.io.*;
import java.util.Properties;

public class Main extends ContractUtil {
    public static void main(String[] args) throws IOException {

        File uncheckedFolder;
        File checkedFolder;

        String configPath = "../res/config.properties";

        Properties properties = new Properties();

        if (args.length > 0) {
            // args passed in configuration:
            // contract.unchecked c:/example/uncheckedFilePath contract.checked c:/example/checkedFilePath
            uncheckedFolder = new File(args[1]);
            checkedFolder = new File(args[3]);

        } else if (System.getProperty("contract.unchecked") != null) {
            // System.getProperty
            uncheckedFolder = new File(System.getProperty("contract.unchecked"));
            checkedFolder = new File(System.getProperty("contract.checked"));

        } else if (properties != null) {
            // config.properties
            properties.load(new FileInputStream(configPath));
            uncheckedFolder = new File(properties.getProperty("contract.unchecked"));
            checkedFolder = new File(properties.getProperty("contract.checked"));

        } else {
            // default
            String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
            uncheckedFolder = new File(downloadPath + File.separator + "Unchecked");
            checkedFolder = new File(downloadPath + File.separator + "Checked");
        }

        // folder validation
        if (uncheckedFolder.exists() && uncheckedFolder.isDirectory()) {
            writeContractsToCsv(readContractsFromJson(uncheckedFolder, checkedFolder));
        } else {
            // unchecked doesn't exist so there are no actions to be taken
            System.exit(0);
        }
    }
}
