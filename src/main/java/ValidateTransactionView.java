import javax.swing.*;

public class ValidateTransactionView extends JButton implements ICheckoutViewComponent  {

    TransactionProcessingModel tpm;
    public ValidateTransactionView(String info, TransactionProcessingModel tpm) {
        super(info);
        this.tpm = tpm;
        this.setEnabled(false);

    }
    public void rerender() {
        this.setEnabled(this.tpm.getTransaction() != null);
    }
}
