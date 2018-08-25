package Controller;

import Model.TransactionProcessingModel;
import View.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import static Util.Utility.debug;

public class CheckoutCounterController extends JPanel {

    public static final int LENGTH = 700;
    public static final int HEIGHT = 300;
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

        JPanel barcodeScanner = new CheckoutCounterBarcodeScannerView(this.tpm, new Dimension(LENGTH / 2, HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT));
        barcodeScanner.setPreferredSize( new Dimension(LENGTH / 2, HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT));

        JPanel transactionInformation = new TransactionInformationView(this.tpm);
        barcodeScanner.setMinimumSize( new Dimension(LENGTH / 2, HEIGHT - HEADER_HEIGHT - FOOTER_HEIGHT));

        JPanel footerPanel = new JPanel();
        JButton validateTransaction = new ValidateTransactionView("Validate checkout", this.tpm);
        JButton checkoutTransaction = new CheckoutTransactionView("Proceed to checkout", this.tpm);
        footerPanel.add(validateTransaction);
        footerPanel.add(checkoutTransaction);

        // add everything to main
        add(titlePanel, BorderLayout.NORTH);
        add(barcodeScanner, BorderLayout.WEST);
        add(transactionInformation, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // Add Action Listeners
        ValidateListener validateListener = new ValidateListener();
        validateTransaction.addActionListener(validateListener);

        CheckoutListener checkoutListener = new CheckoutListener();
        checkoutTransaction.addActionListener(checkoutListener);

        // add component to repaint
        this.tpm.addView((ICheckoutViewComponent) transactionInformation);
        this.tpm.addView((ICheckoutViewComponent) validateTransaction);
        this.tpm.addView((ICheckoutViewComponent) checkoutTransaction);

        debug("Ready for Transaction");
    }

    private boolean fAllowCheckoutTransaction() {
        return tpm.getTransaction() != null && tpm.getTransaction().getValidated();
    }

    /**
     *  Handle Validate options for Checkout
     */
    class ValidateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tpm.validateTransaction();
            debug("Validating");
        }
    }

    /**
     *  Handle Checkout options for Checkout
     */
    class CheckoutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            tpm.resetTransaction();
            JOptionPane.showMessageDialog(null, "Thank you come again!");
        }
    }
}