package com.silviatanas.project.inventory;

import java.io.*;

public class Main extends ContractUtil {
    public static void main(String[] args) {
        // setting folder paths
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        File uncheckedFolder = new File(downloadPath + File.separator + "Unchecked");
        File checkedFolder = new File(downloadPath + File.separator + "Checked");

        if (uncheckedFolder.exists() && uncheckedFolder.isDirectory()) {
            writeContractsToCsv(readContractsFromJson(uncheckedFolder, checkedFolder));
        } else { // unchecked doesn't exist so there are no actions to be taken
            System.exit(0);
        }
    }
}
