import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class CheckoutCounterController extends JPanel {

    public static final int LENGTH = 700;
    public static final int HEIGHT = 500;
    public static final int HEADER_HEIGHT = 25;
    public static final int FOOTER_HEIGHT = 25;

    private TransactionProcessingModel tpm;

    public CheckoutCounterController(TransactionProcessingModel tpm) {
        this.tpm = tpm;

        // to layout the various panels properly
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(LENGTH, HEIGHT));

        // set the top panel
        JLabel title = new JLabel("Checkout Counter");
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(LENGTH, HEADER_HEIGHT));
        titlePanel.add(title);
        titlePanel.setBackground(Color.lightGray);

        JPanel barcodeScanner = new CheckoutCounterBarcodeScanner(this.tpm);
        barcodeScanner.setMaximumSize( new Dimension(LENGTH / 2, HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT));

        JPanel transactionInformation = new TransactionInformation(this.tpm);
        barcodeScanner.setMaximumSize( new Dimension(LENGTH / 2, HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT));

        JPanel footerPanel = new JPanel();
        JButton proceedToCheckout = new JButton("Proceed to checkout");
        footerPanel.add(proceedToCheckout);

        // add everything to main
        add(titlePanel, BorderLayout.NORTH);
        add(barcodeScanner, BorderLayout.WEST);
        add(transactionInformation, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        CheckoutListener hourListener = new CheckoutListener();
        proceedToCheckout.addActionListener(hourListener);
    }

    /**
     *  Handle ComboBox options for Hour
     */
    class CheckoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // TODO:
            // What do we do when we checkout here!
        }
    }
}