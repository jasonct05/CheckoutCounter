package Model;

import Model.DummyTransactionQuery;
import Model.ITransactionQuery;
import Util.Transaction;
import View.ICheckoutViewComponent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
public class TransactionProcessingModel {
    private Transaction transaction;
    private ITransactionQuery transactionQuery;
    private List<ICheckoutViewComponent> viewers; // viewers registered to this model

    public TransactionProcessingModel(boolean isDummy) {
        if (isDummy) {
            this.transactionQuery = new DummyTransactionQuery();
        } else {
            throw new NotImplementedException();
        }
        this.viewers = new ArrayList<ICheckoutViewComponent>();
        resetTransaction();
    }

    public boolean addView(ICheckoutViewComponent view) {
        return this.viewers.add(view);
    }

    public void queryTransaction(String transactionId) {
        if (this.transaction != null)
            throw new IllegalArgumentException("Active Unfinished Util.Transaction in Progress");
        this.transaction = this.transactionQuery.QueryTransactionId(transactionId);
        System.out.println("received transaction ID: " + this.transaction.getTransactionId());
        notifyViewers();
    }

    public void validateTransaction(){
        if(this.transaction == null) {
            throw new IllegalArgumentException();
        }

        this.transaction.setValidated(true);
        // TODO:
        // weight check
        // image recognition shit

        notifyViewers();
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void resetTransaction() {
        this.transaction = null;
        notifyViewers();
    }

    private void notifyViewers() {
        for(ICheckoutViewComponent v: viewers) {
            v.rerender();
        }
    }
}