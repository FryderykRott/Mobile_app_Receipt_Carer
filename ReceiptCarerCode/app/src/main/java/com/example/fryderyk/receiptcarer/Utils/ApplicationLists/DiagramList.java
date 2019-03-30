package com.example.fryderyk.receiptcarer.Utils.ApplicationLists;

import com.example.fryderyk.receiptcarer.Model.DiagramModel;
import com.example.fryderyk.receiptcarer.Model.ReceiptModel;

import java.util.ArrayList;

public class DiagramList extends ListSystem{
    private static final DiagramList ourInstance = new DiagramList();

    public static DiagramList getInstance() {
        return ourInstance;
    }

    private DiagramList() {
        ourListLocal = new ArrayList<DiagramModel>();
        ourListDataBase = new ArrayList<DiagramModel>();

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

    public int addDiagram(DiagramModel diagramModel){

        return 0;
    }

    // more functionallity
}
