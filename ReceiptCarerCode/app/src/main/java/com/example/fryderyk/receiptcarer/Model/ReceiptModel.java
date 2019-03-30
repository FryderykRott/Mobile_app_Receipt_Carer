package com.example.fryderyk.receiptcarer.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReceiptModel {

    public static final int VALIDATION_OK = -1;
    public static final int VALIDATION_WRONG_RECEIPT_NAME = 0;
    public static final int VALIDATION_WRONG_RECEIPT_DATA_OF_PURCHASE = 1;
    public static final int VALIDATION_WRONG_RECEIPT_PRICE = 2;
    public static final int VALIDATION_WRONG_RECEIPT_NOTES = 3;

    private int Receipt_ID;
    private int Shop_ID;
    private int Category_ID;

    private String Receipt_name;
    private String Receipt_date_of_purchase;
    private String Receipt_price;
    private String Receipt_notes;

    private ArrayList<String> tags;

    public ReceiptModel(String receipt_name, int shop_id, int category_ID, String receipt_date_of_purchase, String receipt_price, String receipt_notes) {
        Receipt_ID = 0;
        Shop_ID = shop_id;
        Category_ID = category_ID;

        Receipt_name = receipt_name;
        Receipt_date_of_purchase = receipt_date_of_purchase;
        Receipt_price = receipt_price;
        Receipt_notes = receipt_notes;
        tags = new ArrayList<>();
    }

    public ReceiptModel(ReceiptModel baseReceipt) {
        Receipt_ID = 0;
        setReceipt_date_of_purchase(baseReceipt.getReceipt_date_of_purchase());
        setReceipt_notes(baseReceipt.getReceipt_notes());
        setReceipt_name(baseReceipt.getReceipt_name());
        setCategory_ID(1);
        setShop_ID(1);
        setTags(baseReceipt.getTags());
        setReceipt_price(baseReceipt.getReceipt_price());
    }

    public int getReceipt_ID() {
        return Receipt_ID;
    }

    public void setReceipt_ID(int receipt_ID) {
        Receipt_ID = receipt_ID;
    }

    public int getShop_ID() {
        return Shop_ID;
    }

    public void setShop_ID(int shop_ID) {
        Shop_ID = shop_ID;
    }

    public int getCategory_ID() {
        return Category_ID;
    }

    public void setCategory_ID(int category_ID) {
        Category_ID = category_ID;
    }

    public String getReceipt_date_of_purchase() {
        return Receipt_date_of_purchase;
    }

    public void setReceipt_date_of_purchase(String receipt_date_of_purchase) {
        Receipt_date_of_purchase = receipt_date_of_purchase;
    }

    public String getReceipt_price() {
        return Receipt_price;
    }

    public void setReceipt_price(String receipt_price) {
        Receipt_price = receipt_price;
    }

    public String getReceipt_notes() {
        return Receipt_notes;
    }

    public void setReceipt_notes(String receipt_notes) {
        Receipt_notes = receipt_notes;
    }


    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag){
        tags.add(tag);
    }

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public String getReceipt_name() {
        return Receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        Receipt_name = receipt_name;
    }

    public int validateData() {

        if(!checkDateFormat())
             return VALIDATION_WRONG_RECEIPT_DATA_OF_PURCHASE;

         String name = getReceipt_name();
        if(name == null  || name.equals(""))
            return VALIDATION_WRONG_RECEIPT_NAME;


         if(!checkPriceFormat())
             return VALIDATION_WRONG_RECEIPT_PRICE;

         if(checkNotes())
             return VALIDATION_WRONG_RECEIPT_NOTES;

        return VALIDATION_OK;
    }

    public boolean checkNotes() {
        if(Receipt_notes == null || Receipt_notes.equals(""))
            return true;

        return false;
    }

    public boolean checkDateFormat(){
        if (Receipt_date_of_purchase == null || Receipt_date_of_purchase.equals("")|| !Receipt_date_of_purchase.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");

        try {
            format.parse(Receipt_date_of_purchase);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    public boolean checkPriceFormat(){
        if (Receipt_price == null || Receipt_price.equals(""))
            return false;

        try {
            Double.parseDouble(Receipt_price);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
