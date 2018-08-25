package View;

import Model.TransactionProcessingModel;

import javax.swing.*;

import static Util.Utility.debug;

public class ValidateTransactionView extends JButton implements ICheckoutViewComponent {

    TransactionProcessingModel tpm;
    public ValidateTransactionView(String info, TransactionProcessingModel tpm) {
        super(info);
        this.tpm = tpm;
        this.setEnabled(false);

    }
    public void rerender() {
        debug("rerendering validate transaction button");
        this.setEnabled(this.tpm.getTransaction() != null);
    }
}
