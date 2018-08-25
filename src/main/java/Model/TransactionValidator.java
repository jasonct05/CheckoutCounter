package Model;

import Util.Transaction;

import java.util.Scanner;

public class TransactionValidator {

    public static boolean ValidateTransactionWeight(Transaction transaction) {
        boolean isWeightValidated = false;

        System.out.println("Attempting to Validate Weight...");

        // Simple fake some weight validation
        // TODO: Replace with Arduino / sth
        double expectedWeight = transaction.totalWeight();
        Scanner s = new Scanner(System.in);
        System.out.print("Please input recorded weight: ");
        double weight = s.nextDouble();

        // TODO: Make some buffer for difference
        isWeightValidated = expectedWeight == weight;

        System.out.println("Weight validation status: " + isWeightValidated);
        return isWeightValidated;
    }

    public static boolean ValidateTransactionImage(Transaction transaction) {
        boolean isImageValidated = true;
        // TODO: Do Some Machine Learning Validation

        System.out.println("Attempting to Validate Weight...");
        System.out.println("Weight validation status: " + isImageValidated);

        return isImageValidated;
    }

}
