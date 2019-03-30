package com.example.fryderyk.receiptcarer.Utils.ApplicationLists;

import com.example.fryderyk.receiptcarer.Model.ReceiptModel;

import java.util.ArrayList;


public class ReceiptList extends ListSystem {
    private static final ReceiptList ourInstance = new ReceiptList();

    public static ReceiptList getInstance() {
        return ourInstance;
    }

    private ReceiptList() {
        ourListLocal = new ArrayList<ReceiptModel>();
        ourListDataBase = new ArrayList<ReceiptModel>();

        initiate();
    }

    @Override
    public void synchronizeWithDataBase() {
        // synchronize with database

        // Look for differences in ourListDataBase and ourListLocal
        // and make then reality
    }

    @Override
    public void initiate() {

    }

    public void addReceipt(ReceiptModel receiptModel){
        ourListLocal.add(receiptModel);
    }

}
