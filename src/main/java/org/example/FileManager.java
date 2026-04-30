package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
//bufferreader
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Transaction.csv"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String date = parts[0];
                String time = parts[1];
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);

            }
            reader.close();


        } catch (Exception ex) {
            System.out.println("Something went wrong");
        }
        return transactions;

    }

    public static void writeTransaction(Transaction transaction) {
        try {
            File file = new File("src/main/resources/Transaction.csv");
            FileWriter fileWriter = new FileWriter(file, true);
            if (file.length() > 0) {
                fileWriter.write(System.lineSeparator());
            }
            fileWriter.write(String.format("%s|%s|%s|%s|%f", transaction.getDate(), transaction.getTime(), transaction.getDescription(), transaction.getVendor(), transaction.getAmount()));

            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error Writing to file");
        }
    }
}

