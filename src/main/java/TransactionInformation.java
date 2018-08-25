import javax.swing.*;
import java.awt.*;

public class TransactionInformation extends JPanel implements ICheckoutViewComponent  {

    private TransactionProcessingModel tpm;
    private JTextArea transactionTextArea;

    public TransactionInformation(TransactionProcessingModel tpm) {
        this.tpm = tpm;
        this.transactionTextArea = new JTextArea();
        this.setLayout(new BorderLayout());
        this.add(transactionTextArea, BorderLayout.WEST);
    }

    public void rerender() {
        this.transactionTextArea.setText(null);
        if (this.tpm.getTransaction() != null) {
            this.transactionTextArea.setText(getTransactionString());
        }
    }

    private String getTransactionString(){
        String result = "\n\nWELCOME CUSTOMER:\n\n";
        result += this.tpm.getTransaction().toString() + "\n\n";
        result += "\t\tTOTAL: $" + this.tpm.getTransaction().total();
        return result;
    }

}
