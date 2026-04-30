package org.example;

import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        List<Transaction> transactions = FileManager.getTransactions();

        String choice = "";
        while (!choice.equalsIgnoreCase("X")) {
            System.out.println("Enter D to Add Deposit");
            System.out.println("Enter P to Make Payment(Debit)");
            System.out.println("Enter L to see Ledger Screen");
            System.out.println("Enter X to Exit Application");
            System.out.println("**************");

            choice = scan.nextLine();

            switch (choice) {
                case "D": {
                    System.out.println("Add Deposit");
                    System.out.println("Enter Description:");
                    System.out.println("Enter vendor:");
                    System.out.println("Enter amount");
                    System.out.println("**************");

                    String description = "";
                    description = scan.nextLine();
                    String vendor = "";
                    vendor = scan.nextLine();
                    double amount = 0;
                    try {
                        amount = scan.nextDouble();
                        scan.nextLine();
                    } catch (Exception ex) {
                        System.out.println("Invalid amount.Please enter number.");
                        scan.nextLine();
                    }
                    String date = LocalDate.now().toString();
                    String time = LocalTime.now().toString();

                    Transaction t = new Transaction(date, time, description, vendor, amount);

                    FileManager.writeTransaction(t);

                    break;
                }

                case "P": {
                    System.out.println("Make Payment");
                    System.out.println("Enter Description:");
                    System.out.println("Enter vendor:");
                    System.out.println("Enter amount");
                    System.out.println("**************");
                    String description = "";
                    description = scan.nextLine();
                    String vendor = "";
                    vendor = scan.nextLine();
                    double amount = 0;
                    amount = scan.nextDouble();
                    scan.nextLine();
                    amount = -amount;
                    String date = LocalDate.now().toString();
                    String time = LocalTime.now().toString();

                    Transaction t = new Transaction(date, time, description, vendor, amount);

                    FileManager.writeTransaction(t);
                    break;
                }

                case "L": {
                    System.out.println("Ledger");
                    transactions = FileManager.getTransactions();
                    transactions.sort ((t1,t2)->{

                    int dateCompare = t2.getDate().compareTo(t1.getDate());
                    if (dateCompare !=0)return dateCompare;
                    return t2.getTime().compareTo(t1.getTime());
                });

                    System.out.println("Enter A to print all transactions");
                    System.out.println("Enter D to see deposits only");
                    System.out.println("Enter P to see payments only");
                    System.out.println("Enter R for reports/Standard Custom search");
                    System.out.println("Enter C for Advanced Custom Search ");
                    System.out.println("**************");

                    String input = "";
                    input = scan.nextLine();

                    switch (input) {
                        case "A":
                            System.out.printf("%-12s %-22s %-15s %10s\n",
                                    "Date", "Description", "Vendor", "Amount");
                            System.out.println("--------------------------------------------------------------");

                            for (Transaction t : transactions) {
                                System.out.println(t);
                            }
                            break;

                        case "D":
                            System.out.printf("%-12s %-22s %-15s %10s\n",
                                    "Date", "Description", "Vendor", "Amount");
                            System.out.println("--------------------------------------------------------------");

                            for (Transaction t : transactions) {
                                if (t.getAmount() > 0) {
                                    System.out.println(t);
                                }
                            }
                            break;

                        case "P":
                            System.out.printf("%-12s %-22s %-15s %10s\n",
                                    "Date", "Description", "Vendor", "Amount");
                            System.out.println("--------------------------------------------------------------");

                            for (Transaction t : transactions) {
                                if (t.getAmount() < 0) {
                                    System.out.println(t);
                                }
                            }
                            break;

                        case "R":
                            System.out.println("Press 1 to Search by Vendor");
                            System.out.println("Press 2 for Previous Month");
                            System.out.println("Press 3 to Search by Description");
                            System.out.println("Press 4 to Search by amount");
                            System.out.println("**************");


                            String reportChoice = "";
                            reportChoice = scan.nextLine();
                            switch (reportChoice) {
                                case "1":
                                    System.out.println("Enter vendor name:");

                                    String vendorName = "";
                                    vendorName = scan.nextLine();

                                    for (Transaction t : transactions) {
                                        if (t.getVendor().equalsIgnoreCase(vendorName)) {
                                            System.out.println(t);
                                        }
                                    }
                                    break;

                                case "2":
                                    for (Transaction t : transactions) {
                                        if (t.getDate().startsWith("2026-04")) {
                                            System.out.println(t);
                                        }

                                    }

                                    break;

                                case "3":
                                    System.out.println("Enter description:");
                                    String Description = "";
                                    Description = scan.nextLine();
                                    for (Transaction t : transactions) {
                                        if (t.getDescription().equalsIgnoreCase(Description)) {
                                            System.out.println(t);
                                        }
                                    }
                                    break;

                                case "4":
                                    System.out.println("Enter amount:");
                                    double userAmount = 0;
                                    userAmount = scan.nextDouble();
                                    scan.nextLine();

                                    for (Transaction t : transactions) {
                                        if (t.getAmount() == userAmount) {
                                            System.out.println(t);
                                        }
                                    }
                                    break;
                            }
                            break;

                        case "C":
                            System.out.println("Custom Search");

                            System.out.println("Enter start date (yyyy-mm-dd) or press Enter to skip:");
                            String startDate = scan.nextLine();

                            System.out.println("Enter end date (yyyy-mm-dd) or press Enter to skip:");
                            String endDate = scan.nextLine();

                            System.out.println("Enter description or press Enter to skip:");
                            String descriptionSearch = scan.nextLine();

                            System.out.println("Enter vendor or press Enter to skip:");
                            String vendorSearch = scan.nextLine();

                            System.out.println("Enter amount or press Enter to skip:");
                            String amountInput = scan.nextLine();

                            double amountSearch = 0;
                            boolean checkAmount = false;

                            if (!amountInput.isEmpty()) {
                                amountSearch = Double.parseDouble(amountInput);
                                checkAmount = true;
                            }

                            for (Transaction t : transactions) {

                                boolean matches = true;

                                if (!startDate.isEmpty() &&
                                        t.getDate().compareTo(startDate) < 0) {
                                    matches = false;
                                }

                                if (!endDate.isEmpty() &&
                                        t.getDate().compareTo(endDate) > 0) {
                                    matches = false;
                                }

                                if (!descriptionSearch.isEmpty() &&
                                        !t.getDescription().equalsIgnoreCase(descriptionSearch)) {
                                    matches = false;
                                }

                                if (!vendorSearch.isEmpty() &&
                                        !t.getVendor().equalsIgnoreCase(vendorSearch)) {
                                    matches = false;
                                }

                                if (checkAmount &&
                                        t.getAmount() != amountSearch) {
                                    matches = false;
                                }

                                if (matches) {
                                    System.out.println(t);
                                }
                            }

                            break;

                    }
                    break;
                }


                case "X":
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid Choice");
              }
            }

        }

    }
