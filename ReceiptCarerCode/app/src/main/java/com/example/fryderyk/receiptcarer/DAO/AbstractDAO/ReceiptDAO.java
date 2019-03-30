package com.example.fryderyk.receiptcarer.DAO.AbstractDAO;

import com.example.fryderyk.receiptcarer.Model.ReceiptModel;

import java.util.ArrayList;

public abstract class ReceiptDAO extends DAO{

    public abstract int insertReceipt(ReceiptModel receipt);
    public abstract boolean deleteReceipt(int Receipt_ID);
    public abstract ReceiptModel findReceipt(int Receipt_ID);
    public abstract ArrayList<ReceiptModel> findAllReceipts(int User_ID);
    public abstract boolean updateReceipt(ReceiptModel receipt);

}
