package View;

import Model.TransactionProcessingModel;

import javax.swing.*;

import static Util.Utility.debug;

public class CheckoutTransactionView extends JButton implements ICheckoutViewComponent {

    TransactionProcessingModel tpm;
    public CheckoutTransactionView(String info, TransactionProcessingModel tpm) {
        super(info);
        this.tpm = tpm;
        this.setEnabled(false);
    }

    public void rerender() {
        debug("rerendering checkout button");
        this.setEnabled(this.tpm.getTransaction() != null && this.tpm.getTransaction().getValidated());
    }
}
