package com.example.fryderyk.receiptcarer.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiagramModel {

    public final static int VAL_OK = -1;
    public final static int VAL_DATA_OD_WIEKSZA_OD_DO = 1;
    public final static int VAL_DATA_Do_MNIEJSZA_OD_DO = 2;
    public final static int VAL_DATA_OD_ = 3;
    public final static int VAL_DATA_DO = 4;


    private String odData;
    private String doData;
    private String rodzajWykresu;
    private String podzialCzasowy;
    private ArrayList<String> categories;

    public DiagramModel(String odData, String doData, String rodzajWykresu, String przedzialCZasowy){
        this.odData = odData;
        this.doData = doData;
        this.rodzajWykresu = rodzajWykresu;
        this.podzialCzasowy = przedzialCZasowy;
        this.categories = new ArrayList<>();
    }

    public String getOdData() {
        return odData;
    }

    public void setOdData(String odData) {
        this.odData = odData;
    }

    public String getDoData() {
        return doData;
    }

    public void setDoData(String doData) {
        this.doData = doData;
    }

    public String getRodzajWykresu() {
        return rodzajWykresu;
    }

    public void setRodzajWykresu(String rodzajWykresu) {
        this.rodzajWykresu = rodzajWykresu;
    }

    public String getPodzialCzasowy() {
        return podzialCzasowy;
    }

    public void setPodzialCzasowy(String podzialCzasowy) {
        this.podzialCzasowy = podzialCzasowy;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void addCategories(String cat){
        categories.add(cat);
    }

    public int validateData(){

        if(!checkDateFormat(odData))
            return VAL_DATA_OD_;


        if(!checkDateFormat(doData))
            return VAL_DATA_DO;


        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");

        Date odD = null;
        Date doD = null;
        try {
            odD = format.parse(odData);
            doD = format.parse(doData);

            if(odD.after(doD))
                return VAL_DATA_OD_WIEKSZA_OD_DO;

            if(doD.before(odD))
                return VAL_DATA_Do_MNIEJSZA_OD_DO;
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return VAL_OK;
    }

    public boolean checkDateFormat(String date){
        if (date == null || date.equals("")|| !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");

        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }
}
