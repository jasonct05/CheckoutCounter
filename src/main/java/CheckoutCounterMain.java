import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CheckoutCounterMain {
    public static final boolean DUMMY_TRANSACTION_QUERY = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Express Checkout");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CheckoutCounterController controller = new CheckoutCounterController(new TransactionProcessingModel(DUMMY_TRANSACTION_QUERY));
        frame.add(controller);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}