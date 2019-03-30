package com.example.fryderyk.receiptcarer.DAO.PostgreSQLDAO;

import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DAOFactory;
import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DiagramDAO;
import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.ReceiptDAO;

/**
 * Fabryka do tworzenie obiektów DAO. Niekompletna. W trakcie pisania.
 */
public class PostgreSQLDAOFactory extends DAOFactory {
    @Override
    public ReceiptDAO getReceiptDAO() {
        return null;
    }

    @Override
    public DiagramDAO getDiagramDAO() {
        return null;
    }

}
