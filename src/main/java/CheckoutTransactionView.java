import javax.swing.*;

public class CheckoutTransactionView extends JButton implements ICheckoutViewComponent  {

    TransactionProcessingModel tpm;
    public CheckoutTransactionView(String info, TransactionProcessingModel tpm) {
        super(info);
        this.tpm = tpm;
        this.setEnabled(false);
    }

    public void rerender() {
        System.out.println("rerendering");
        this.setEnabled(this.tpm.getTransaction() != null && this.tpm.getTransaction().getValidated());
    }
}
