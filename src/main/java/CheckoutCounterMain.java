import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CheckoutCounterMain {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Express Checkout");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CheckoutCounterController controller = new CheckoutCounterController();
        frame.add(controller);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}