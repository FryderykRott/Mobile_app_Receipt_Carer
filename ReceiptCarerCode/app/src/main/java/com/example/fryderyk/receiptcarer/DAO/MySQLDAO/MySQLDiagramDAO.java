package com.example.fryderyk.receiptcarer.DAO.MySQLDAO;

import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DiagramDAO;
import com.example.fryderyk.receiptcarer.Model.DiagramModel;

import java.sql.Connection;
import java.util.ArrayList;

public class MySQLDiagramDAO extends DiagramDAO {
    /**
     * @param diagram do wykonania zapytania Włożenia do bazy danych wiersza, potrzebny jest obiekt
     *                typu DiagramModel. W innym wypadku zapytanie się nie wykona.
     * @return Zwraca zawsze wartość 0, gdyż pełna obsługa bazy danych jest w trakcie prac.
     */
    @Override
    public int insertDiagram(DiagramModel diagram) {
        // Połącz się z bazą
        Connection con = MySQLDAOFactory.createConnection();

        // Stwórz zapytanie zapytanie

        String querry = "INSERT INTO `charts` " +
                "(`Chart_ID`, `Data_From`, `Data_To`, `Char_type_ID`, `User_ID`, `Char_image_path`) " +
                "VALUES" +
                " (NULL, '2019-01-01', '2019-01-30', '3', '1', 'c:\\\\chars\\\\char_4')";

        // Wykonaj zapytanie
        DoQuerry doQuerry = new DoQuerry(querry, con);
        doQuerry.execute();

        //Obsługa wykoanengo zapytania
        return 0;
    }

    @Override
    public boolean deleteDiagram(int Diagram_ID) {
        return false;
    }

    @Override
    public DiagramModel findDiagram(int Diagram_ID) {
        return null;
    }

    @Override
    public ArrayList<DiagramModel> findAllDiagrams(int User_ID) {
        return null;
    }

    @Override
    public boolean updateDiagram(DiagramModel receipt) {
        return false;
    }
}
