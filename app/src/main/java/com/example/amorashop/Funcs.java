package com.example.amorashop;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Funcs {

//    Capitalize String
    public String toCapitalized(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) { // Handle empty words (e.g., multiple spaces)
                capitalized.append(word.substring(0, 1).toUpperCase())
                        .append(word.substring(1))
                        .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

//    Format string to idr
    public static String formatIdr(String amountString) {
        try {
            // Remove any non-digit characters except the decimal point
            String cleanAmountString = amountString.replaceAll("[^\\d.]", "");

            // Parse the cleaned string as a double
            double amount = Double.parseDouble(cleanAmountString);

            // Create a NumberFormat instance for Indonesian Rupiah (IDR)
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

            // Cast to DecimalFormat to customize the pattern
            DecimalFormat decimalFormat = (DecimalFormat) currencyFormat;

            // Set the pattern to remove trailing zeros after the decimal point
            decimalFormat.applyPattern("Rp#,##0.##"); // Allows 0, 1, or 2 decimal places

            // Format the amount as currency
            return currencyFormat.format(amount);

        } catch (NumberFormatException e) {
            // Handle invalid input (e.g., non-numeric string)
            return "Invalid Amount";
        }
    }
}
