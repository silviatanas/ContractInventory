package com.silviatanas.project.inventory;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class ContractUtil {
    public static Contract[] readContractsFromJson(File uncheckedFolder, File checkedFolder) throws IOException {
        // gson for json conversion
        Gson gson = new Gson();

        // list to store the contracts to pass them over to write method
        ArrayList<Contract> contractList = new ArrayList<>();

        // validate if folder paths already exist
        for (File file : uncheckedFolder.listFiles()) {

            // working only with json files
            if (file.getAbsolutePath().endsWith(".json")) {

                // convert json string to Contract object
                String fileContent = null;
                fileContent = Files.readString(file.toPath());
                Contract contract = gson.fromJson(fileContent, Contract.class);

                // add each object to the list
                contractList.add(contract);

                // make Checked folder if it doesn't exist
                if (!checkedFolder.exists()) {
                    checkedFolder.mkdir();
                }

                // move processed files to Checked folder
                if (checkedFolder.isDirectory()) {
                    Files.move(Paths.get(file.getAbsolutePath()), Paths.get(checkedFolder + File.separator + file.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }

        return contractList.toArray(new Contract[0]);
    }

    public static void writeContractsToCsv(Contract[] contractList) throws IOException {

        BufferedWriter writer;

        // proper date formatting for the milliseconds data
        DateFormat dateConvert = new SimpleDateFormat("yyyy-MM-dd");


        // writing header for csv file
        writer = new BufferedWriter(new FileWriter("contracts.csv"));
        writer.write("Contract Number, Customer ID, Customer Name, Billing System ID, " +
                "Contract Start, Contract End, Environment Name, Row, " +
                "Unit, Product Code, Environment Type, Usage Type, " +
                "swAG Cloud, Related Contracts");
        writer.newLine();

        // iterating the data from read method
        for (Contract contract : contractList) {
            for (Environment e : contract.getEnvironments()) {
                // licenseInfo to LicenseInfo objects
                e.setLicenseInfoObjects(parseLicenseInfo(e.getLicenseInfo()));

                for (LicenseInfo l : e.getLicenseInfoObjects()) {
                    for (Object p : l.getProductCodes()) {

                        // contract data written into csv

                        // main param
                        writer.write(contract.getContractNumber() + ", ");
                        writer.write(contract.getCustomerId() + ", ");
                        writer.write(contract.getCustomerName() + ", ");
                        writer.write(contract.getBillingSystemId() + ", ");
                        writer.write(dateConvert.format(contract.getStartDate()) + ", ");
                        writer.write(dateConvert.format(contract.getEndDate()) + ", ");

                        // environments
                        writer.write(e.getName() + ", ");
                        // licenseInfo
                        writer.write(l.getRow() + ", ");
                        writer.write(l.getUnit() + ", ");
                        writer.write(p + ", ");
                        // licenseInfo end
                        writer.write(e.getEnvironmentType() + ", ");
                        writer.write(e.getUsageType() + ", ");
                        writer.write(e.isSwAGCloud() + ", ");
                        writer.write(Arrays.toString(contract.getRelatedContracts())
                                .replace("[", "")
                                .replace("]", ""));

                        writer.newLine();
                        writer.flush();
                    }
                }
            }
        }
    }

    public static LicenseInfo[] parseLicenseInfo(String info) {
        Gson gson = new Gson();

        LicenseInfo[] licenseInfo = gson.fromJson(info, LicenseInfo[].class);
        return licenseInfo;
    }
}
