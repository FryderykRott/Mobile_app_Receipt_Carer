package com.example.fryderyk.receiptcarer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.fryderyk.receiptcarer.Model.DiagramModel;

import java.util.ArrayList;
import java.util.Calendar;

public class CreateDiagram extends AppCompatActivity {

    private static final int OD = 0;
    private static final int DO = 1;
//    Diagram model
    DiagramModel diagram;

//    Od
    EditText editTextodDate;
    ImageButton buttonOdDate;

//  Do
    EditText editTextDoDate;
    ImageButton buttonDoDate;

//    Przedział czasowy
    Spinner spinnerPrzedzialCzasowy;

//    Rodzaj wykresu
    Spinner spinnerRodzajWykresu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diagram);

        diagram = new DiagramModel("", "", "", "");
        declarate();
    }

    private void declarate() {

        dataPickerOd();
        dataPickerDo();
        przedzialCzasowy();
        rodzajWykresu();

    }

    //    Od Do
    private void dataPickerOd() {
        editTextodDate = findViewById(R.id.editTextDiagramOdCalendar);
        buttonOdDate = findViewById(R.id.buttonDiagramOdCalendar);


        buttonOdDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar(editTextodDate, OD);
            }
        });

        editTextodDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar(editTextodDate, OD);
            }
        });

        editTextodDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                diagram.setOdData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    private void dataPickerDo() {
        editTextDoDate = findViewById(R.id.editTextDiagramDoCalendar);
        buttonDoDate = findViewById(R.id.buttonDiagramDoCalendar);

        buttonDoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar(editTextDoDate, DO);
            }
        });
        editTextDoDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar(editTextDoDate, DO);
            }
        });

        editTextDoDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                diagram.setDoData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showAndFindDateWithCalendar(final EditText et, final int witchData){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        DatePickerDialog picker = new DatePickerDialog(CreateDiagram.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String month_end;
                        if(monthOfYear < 10){

                            month_end = "0" + (monthOfYear + 1);
                        }
                        else
                            month_end = (monthOfYear + 1) + "";

                        String day_end;
                        if(dayOfMonth < 10){

                            day_end  = "0" + dayOfMonth ;
                        }
                        else
                            day_end = dayOfMonth + "";

                        String date = day_end + "/" + month_end + "/" + year;
                        et.setText(date);
//                        model
                        if(witchData == OD)
                            diagram.setOdData(et.getText().toString());
                        else if(witchData == DO)
                            diagram.setDoData(et.getText().toString());

                    }
                }, year, month, day);
        picker.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_accept) {
            int result = diagram.validateData();

            switch (result){
                case DiagramModel.VAL_OK:
                    Intent intent = new Intent(this, DiagramReview.class);

                    startActivity(intent);
                    finish();
                    break;
                case DiagramModel.VAL_DATA_DO:
                    Snackbar.make(editTextDoDate , "Błędny format daty DO!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    break;
                case DiagramModel.VAL_DATA_OD_:
                    Snackbar.make(editTextDoDate , "Błędny format daty OD!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    break;
                case DiagramModel.VAL_DATA_Do_MNIEJSZA_OD_DO:
                    Snackbar.make(editTextDoDate , "Data DO musi być większa od daty OD!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    break;
                case DiagramModel.VAL_DATA_OD_WIEKSZA_OD_DO:
                    Snackbar.make(editTextDoDate , "Data OD musi być mniejsza od daty DO!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    break;
                default:
                    Snackbar.make(editTextDoDate , "TEST!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    break;
            }


        }

        return super.onOptionsItemSelected(item);
    }

    //    Przedział czasowy
    private final ArrayList<String> przedzialy = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private void przedzialCzasowy() {
        przedzialy.add("Lata");
        przedzialy.add("Miesiące");
        przedzialy.add("Dni");
        przedzialy.add("Kwartały");

        spinnerPrzedzialCzasowy = findViewById(R.id.PrzedzialCzasowySpinner);
        adapter = new ArrayAdapter<>(CreateDiagram.this,
                android.R.layout.simple_spinner_item, przedzialy);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrzedzialCzasowy.setAdapter(adapter);
        spinnerPrzedzialCzasowy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diagram.setPodzialCzasowy(przedzialy.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //    Przedział czasowy
    private final ArrayList<String> rodzaje = new ArrayList<>();
    ArrayAdapter<String> adapterRodziaj;
    private void rodzajWykresu() {
        rodzaje.add("Kołowy");
        rodzaje.add("Słupkowy");
        rodzaje.add("3D");

        spinnerRodzajWykresu = findViewById(R.id.RodzajWykresySpinner);
        adapterRodziaj = new ArrayAdapter<>(CreateDiagram.this,
                android.R.layout.simple_spinner_item, rodzaje);

        adapterRodziaj.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRodzajWykresu.setAdapter(adapterRodziaj);
        spinnerRodzajWykresu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                diagram.setRodzajWykresu(rodzaje.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
