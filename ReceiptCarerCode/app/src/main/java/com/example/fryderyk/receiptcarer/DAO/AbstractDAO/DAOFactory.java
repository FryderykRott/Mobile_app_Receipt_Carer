package com.example.fryderyk.receiptcarer.DAO.AbstractDAO;

import com.example.fryderyk.receiptcarer.DAO.MySQLDAO.MySQLDAOFactory;
import com.example.fryderyk.receiptcarer.DAO.PostgreSQLDAO.PostgreSQLDAOFactory;

/**
 * Klasa abstrakcyjna będąca wierzchołkiem wzorca Abstrakcyjnej Fabryki + DAO
 */
public abstract class DAOFactory {

    /**
     * Stała określająca czy korzystana będzie baza danych oparta na języku MYSQL
     */
    public static final int MYSQL = 0;
    /**
     * Stała określająca czy korzystana będzie baza danych oparta na języku POSTGRESQL
     */
    public static final int POSTGRESQL = 1;

    /**
     * @return Zwraca Fabryke Paragonów DAO
     */
    public abstract ReceiptDAO getReceiptDAO();

    /**
     * @return Zwraca Fabryke Wykresów DAO
     */
    public abstract DiagramDAO getDiagramDAO();


    /**
     * @param whichFactor parametr ten określa, które fakbrykę treba wyprodukować, fakbrykę
     *                    MySQL czy fabrykę postgreSQL
     * @return
     */
    public static DAOFactory getDAOFaktory(int whichFactor){
        switch (whichFactor){
            case MYSQL:
                return new MySQLDAOFactory();
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            default:
                return null;
        }
    }
}
