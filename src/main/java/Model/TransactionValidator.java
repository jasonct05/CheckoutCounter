package Model;

import Util.Item;
import Util.Transaction;

import java.util.List;
import java.util.Scanner;

import static Util.Utility.debug;

public class TransactionValidator {

    public static boolean ValidateTransactionWeight(Transaction transaction) {
        boolean isWeightValidated = false;
        System.out.println("---------------------------");
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
        System.out.println("---------------------------");
        return isWeightValidated;
    }

    public static boolean ValidateTransactionImage(Transaction transaction) {
        boolean isImageValidated = false;
        String imagePath = "ShoppingBasket";
        System.out.println("---------------------------");
        System.out.println("Attempting to Validate Image...");
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Taking picture in " + i + " second(s)");
                Thread.sleep(1000);
            }
            transaction.setRequestImageURL(imagePath);
            Thread.sleep(1000);
        } catch (Exception e){
            // do nothing
        }

        isImageValidated = imageValidation(transaction, imagePath);
        System.out.println("Image validation status: " + isImageValidated);
        System.out.println("---------------------------");
        return isImageValidated;
    }

    private static boolean imageValidation(Transaction t, String localImage) {
        List<Item> actualItems = null; // TODO: Replace with call to AZURE stuff
        List<Item> expectedItems = t.getItems();

        // do some fancy compare
        t.setRequestImageURL(null);
        return true;
    }
}
