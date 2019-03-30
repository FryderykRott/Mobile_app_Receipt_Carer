package com.example.fryderyk.receiptcarer.DAO.AbstractDAO;

import com.example.fryderyk.receiptcarer.Model.DiagramModel;

import java.util.ArrayList;

public abstract class DiagramDAO extends DAO {

    public abstract int insertDiagram(DiagramModel diagram);
    public abstract boolean deleteDiagram(int Diagram_ID);
    public abstract DiagramModel findDiagram(int Diagram_ID);
    public abstract ArrayList<DiagramModel> findAllDiagrams(int User_ID);
    public abstract boolean updateDiagram(DiagramModel receipt);
}
