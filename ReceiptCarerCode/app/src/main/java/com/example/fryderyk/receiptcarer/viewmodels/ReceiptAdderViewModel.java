package com.example.fryderyk.receiptcarer.viewmodels;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.example.fryderyk.receiptcarer.Model.ReceiptModel;
import com.example.fryderyk.receiptcarer.Utils.ApplicationLists.ReceiptList;

public class ReceiptAdderViewModel extends BaseObservable {
    private ReceiptModel receipt;

    @Bindable
    private String toastMessage = null;

    @Bindable
    private String test = null;


    public ReceiptAdderViewModel(){
        receipt = new ReceiptModel("", 1, 1, "19/01/2018", "", "");
    }

    public String getToastMessage() {
        return toastMessage;
    }

    private void setToastMessage(String toastMessage) {

        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
    }

    public void afterDataZakupuTextChanged(CharSequence s) {
        receipt.setReceipt_date_of_purchase(s.toString());
    }

    public void onDataPickerCklicked() {
        setToastMessage("TEST");
        ReceiptList.getInstance().addReceipt(receipt);
        ReceiptList.getInstance().synchronizeWithDataBase();
    }



    public void onDataPicker(){
        setTest("TEST");
    }

    public void setTest(String heh) {
        test = heh;
        notifyPropertyChanged(BR.test);
    }

    public String getTest(){
        return test;
    }
}
