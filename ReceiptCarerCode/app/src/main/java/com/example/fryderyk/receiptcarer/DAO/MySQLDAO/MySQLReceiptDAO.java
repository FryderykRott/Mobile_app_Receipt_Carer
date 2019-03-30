package com.example.fryderyk.receiptcarer.DAO.MySQLDAO;

import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.ReceiptDAO;
import com.example.fryderyk.receiptcarer.Model.ReceiptModel;

import java.sql.Connection;
import java.util.ArrayList;


public class MySQLReceiptDAO extends ReceiptDAO {

    /**
     * @param receipt do wykonania zapytania Włożenia do bazy danych wiersza, potrzebny jest obiekt
     *                typu ReceiptModel. W innym wypadku zapytanie się nie wykona.
     * @return Zwraca zawsze wartość 0, gdyż pełna obsługa bazy danych jest w trakcie prac.
     */
    @Override
    public int insertReceipt(ReceiptModel receipt) {
        // Połącz się z bazą
        Connection con = MySQLDAOFactory.createConnection();

        // Stwórz zapytanie zapytanie

        String querry = "INSERT INTO `receipts` " +
                "(`Receipt_ID`, `Receipt_Name`, `Shop_ID`, `Category_ID`, `User_ID`, `Receipt_date_of_purchase`, `Receipt_price`, `Receipt_notes`) " +
                "VALUES (NULL, " +
                "'"+receipt.getReceipt_name()+"', " +
                "'1', '1', '1', " +
                "'2019-01-16', " +
                "'3.4', " +
                "'dsaads')";

        // Wykonaj zapytanie
        DoQuerry doQuerry = new DoQuerry(querry, con);
        doQuerry.execute();

        //Obsługa wykoanengo zapytania

        return 0;
    }

    @Override
    public boolean deleteReceipt(int Receipt_ID) {
        return false;
    }

    @Override
    public ReceiptModel findReceipt(int Receipt_ID) {
        return null;
    }

    @Override
    public ArrayList<ReceiptModel> findAllReceipts(int User_ID) {
        return null;
    }

    @Override
    public boolean updateReceipt(ReceiptModel receipt) {
        return false;
    }

}
