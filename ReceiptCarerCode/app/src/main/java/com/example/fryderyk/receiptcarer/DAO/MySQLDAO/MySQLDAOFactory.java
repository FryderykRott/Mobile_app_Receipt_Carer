package com.example.fryderyk.receiptcarer.DAO.MySQLDAO;

import android.os.StrictMode;
import android.util.Log;

import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DAOFactory;
import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DiagramDAO;
import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.ReceiptDAO;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Klasa tworząca Obiekty DAO posługujące się MySQL
 */
public class MySQLDAOFactory extends DAOFactory {
    private static String classs = "com.mysql.jdbc.Driver";

    private static String url = "jdbc:mysql://10.0.2.2:3306/receipt_carer_db";
    private static String un = "root";
    private static String password = "";

    /**
     * @return zwraca połączenie z lokalną bazą danych.
     */
    static Connection createConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection conn = null;
        try {

            Class.forName(classs);

            conn = DriverManager.getConnection(url, un, password);

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return conn;
    }

    /**
     * @return zwraca MySQLReceiptDAO co pozwala na działanie w obrębie MySQL
     */
    @Override
    public ReceiptDAO getReceiptDAO() {
        return new MySQLReceiptDAO();
    }

    @Override
    public DiagramDAO getDiagramDAO() {
        return null;
    }

}
