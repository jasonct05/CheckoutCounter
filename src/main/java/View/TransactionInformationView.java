package View;

import Model.TransactionProcessingModel;

import javax.swing.*;
import java.awt.*;

public class TransactionInformationView extends JPanel implements ICheckoutViewComponent {

    private TransactionProcessingModel tpm;
    private JTextArea transactionTextArea;

    public TransactionInformationView(TransactionProcessingModel tpm) {
        this.tpm = tpm;
        this.transactionTextArea = new JTextArea();
        this.setLayout(new BorderLayout());
        this.add(transactionTextArea, BorderLayout.WEST);
    }

    public void rerender() {
        System.out.println("rerendering information panel");
        this.transactionTextArea.setText(null);
        if (this.tpm.getTransaction() != null) {
            this.transactionTextArea.setText(getTransactionString());
        }
    }

    private String getTransactionString(){
        String result = "\n\nWELCOME CUSTOMER:\n\n";
        result += this.tpm.getTransaction().toString() + "\n\n";
        result += "\t\tTOTAL: $" + this.tpm.getTransaction().totalPrice();
        return result;
    }

}
