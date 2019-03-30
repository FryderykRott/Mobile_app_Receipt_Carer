package com.example.fryderyk.receiptcarer.Utils.ApplicationLists;

import java.util.ArrayList;

public abstract class ListSystem {

    ArrayList ourListLocal;
    ArrayList ourListDataBase;

    public abstract void synchronizeWithDataBase();
    public abstract void initiate();
}
