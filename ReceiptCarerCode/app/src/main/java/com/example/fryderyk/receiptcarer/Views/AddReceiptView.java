package com.example.fryderyk.receiptcarer.Views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fryderyk.receiptcarer.R;
import com.example.fryderyk.receiptcarer.databinding.ActivityAddReceiptViewBinding;
import com.example.fryderyk.receiptcarer.viewmodels.ReceiptAdderViewModel;

import java.util.Calendar;

public class AddReceiptView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityAddReceiptViewBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_receipt_view);
        activityMainBinding.setViewModel(new ReceiptAdderViewModel());
        activityMainBinding.executePendingBindings();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @BindingAdapter({"calendarDialog"})
    public static void runMe2(View view, String message){
        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        final EditText eText = view.findViewById(R.id.dataZakupyEditText);

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        DatePickerDialog picker = new DatePickerDialog(view.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
