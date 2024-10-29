package com.bse.amount;

public class RupeesToWords {

    private static final String[] units = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] thousands = {
        "", "Thousand", "Lakh", "Crore"
    };

    public static String convert(double amount) {
        if (amount == 0) {
            return "Zero Rupees";
        }

        // Split the amount into rupees and paise
        int rupees = (int) amount;
        int paise = (int) Math.round((amount - rupees) * 100);

        String rupeesPart = convertRupees(rupees) + "Rupees";
        String paisePart = (paise > 0) ? " and " + convertRupees(paise) + "Paise" : "";

        return rupeesPart + paisePart;
    }

    private static String convertRupees(int number) {
        if (number == 0) {
            return "";
        }

        String words = "";
        int index = 0;

        while (number > 0) {
            if (number % 1000 != 0) {
                words = convertLessThanThousand(number % 1000) + thousands[index] + " " + words;
            }
            number /= 1000;
            index++;
        }

        return words.trim() + " ";
    }

    private static String convertLessThanThousand(int number) {
        String words;

        if (number % 100 < 20) {
            words = units[number % 100];
            number /= 100;
        } else {
            words = units[number % 10];
            number /= 10;

            words = tens[number % 10] + " " + words;
            number /= 10;
        }

        if (number == 0) {
            return words;
        }

        return units[number] + " Hundred " + words;
    }

    public static void main(String[] args) {
        int amount = 12340;
        System.out.println("The amount " + amount + " in words is: " + convert(amount));
    }
}

