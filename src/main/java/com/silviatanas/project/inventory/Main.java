package com.silviatanas.project.inventory;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Main {
    public static void utility(File uncheckedFolder, File checkedFolder) {
        Gson gson = new Gson();

        // proper date formatting for the milliseconds data
        DateFormat dateConvert = new SimpleDateFormat("yyyy-MM-dd");

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("csv"));
            writer.write("Contract Number, Customer ID, Customer Name, Billing System ID, " +
                    "Contract Start, Contract End, Environments, License Info, " +
                    "Environment Type, Usage Type, swAG Cloud, Related Contracts");
            writer.newLine();

            // validate if folder paths already exist
            for (File file : uncheckedFolder.listFiles()) {
                System.out.println("3");
                if (file.getAbsolutePath().endsWith(".json")) {
                    System.out.println("4");
                    // convert json string to contract object
                    String fileContent = null;
                    try {
                        fileContent = Files.readString(file.toPath());
                        System.out.println("5");

                        Contract contract = gson.fromJson(fileContent, Contract.class);

                        // make checked folder if it doesn't exist
                        if (!checkedFolder.exists()) {
                            checkedFolder.mkdir();
                            System.out.println("6");
                        }

                        // move processed files
                        if (checkedFolder.isDirectory()) {
                            System.out.println("7");
                            Files.move(Paths.get(file.getAbsolutePath()), Paths.get(checkedFolder + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        }

                        for (Environment e : contract.getEnvironments()) {
                            System.out.println("8");

                            // data written into csv
                            writer.write(contract.getContractNumber() + ", ");
                            writer.write(contract.getCustomerId() + ", ");
                            writer.write(contract.getCustomerName() + ", ");
                            writer.write(contract.getBillingSystemId() + ", ");
                            writer.write(dateConvert.format(contract.getStartDate()) + ", ");
                            writer.write(dateConvert.format(contract.getEndDate()) + ", ");

                            writer.newLine();
                            writer.write(e.name + ", ");
                            writer.write(e.licenseInfo + ", ");
                            writer.write(e.environmentType + ", ");
                            writer.write(e.usageType + ", ");
                            writer.write(e.swAGCloud + ", ");

                            writer.newLine();
                            e.setLicenseInfoObjects(parseLicenseInfo(e.licenseInfo));
                            for (LicenseInfo l : e.licenseInfoObjects) {
                                writer.write(l.getRow() + ", ");
                                writer.write(l.getUnit() + ", ");
                                writer.write(Arrays.toString(l.getProductCodes()));
                                writer.newLine();
                            }
                            writer.write(Arrays.toString(e.licenseInfoObjects));

                            writer.newLine();
                            writer.write(Arrays.toString(contract.getRelatedContracts()));
                            writer.newLine();
                            writer.flush();
                        }


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing into CSV");
        }
    }

    public static LicenseInfo[] parseLicenseInfo(String info) {
        Gson gson = new Gson();

        LicenseInfo[] licenseInfo = gson.fromJson(info, LicenseInfo[].class);
        return licenseInfo;
    }

    public static void main(String[] args) {
        // setting folder paths
        String downloadPath = System.getProperty("user.home") + File.separator + "Downloads";
        File uncheckedFolder = new File(downloadPath + File.separator + "Unchecked");
        File checkedFolder = new File(downloadPath + File.separator + "Checked");
        System.out.println("1");

        if (uncheckedFolder.exists() && uncheckedFolder.isDirectory()) {
            System.out.println("2");
            utility(uncheckedFolder, checkedFolder);
        } else { // unchecked doesn't exist so there's no actions to be taken
            System.out.println("3");
            System.exit(0);
        }
    }
}
