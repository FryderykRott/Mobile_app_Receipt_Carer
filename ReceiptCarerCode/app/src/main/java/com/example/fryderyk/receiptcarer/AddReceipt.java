package com.example.fryderyk.receiptcarer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
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
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.DAOFactory;
import com.example.fryderyk.receiptcarer.DAO.AbstractDAO.ReceiptDAO;
import com.example.fryderyk.receiptcarer.Model.ReceiptModel;
import com.example.fryderyk.receiptcarer.Utils.CostumAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public class AddReceipt extends AppCompatActivity {
//  Model
    ReceiptModel receipt;

//    Data Zakupów
    EditText editTekstDataZakupow;
    ImageButton buttonCalendar;

//    Paragon Nazwa
    EditText editTextParagonNazwa;
    TextView textParagonNazwaIlosc;

//    Cena
    EditText editTextCena;

//    Kategoria
    Spinner spinnerKategoria;

//    NazwaSklepu
    EditText editTextNazwaSklepu;

//    Opis
    EditText editTextOpis;
    TextView textOpisIlosc;

//    Znaczniki
    EditText editTextTag;
    ImageButton buttonAddTag;
    TextView textTagIlosc;
    TextView textTags;

//    Produkty
    Spinner spinnerProduct;
    ImageButton buttonProduct;
    TextView textProductIlosc;
    TextView textProducs;

//    Gwarancja
    SeekBar seekBarGwarancja;
    TextView textGwarancjaIlosc;

//    Zdjecia
    ImageButton buttonCamera;
    ImageButton buttonGaleria;

    ListView listViewZdjecia;
    ImageView imageProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);

        receipt = new ReceiptModel("",1, 1, "", "", "");

        declarate();
        bitmap = (Bitmap) getIntent().getExtras().get("data");
        if(bitmap != null)
            uzupelnij();

    }

    private void uzupelnij() {
        editTekstDataZakupow.setText("02/02/2005");

        editTextParagonNazwa.setText("Zakupy do domu");
        textParagonNazwaIlosc.setText("14");

        editTextNazwaSklepu.setText("Tesco");

        editTextCena.setText("200.40");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_accept) {
//            sprawdz czy wszystkie dane są poprawne!
            if(neverUse == false){
                onCreateInformationDialog().show();
            }else
                doAddStuff();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doAddStuff() {
        int result = receipt.validateData();
//        switch
        switch (result){
            case ReceiptModel.VALIDATION_OK:
                DAOFactory mySQLDAOFactory = DAOFactory.getDAOFaktory(DAOFactory.MYSQL);

                assert mySQLDAOFactory != null;
                ReceiptDAO receiptDAO = mySQLDAOFactory.getReceiptDAO();

                receiptDAO.insertReceipt(receipt);


//                Snackbar.make(editTextCena , "Dodano paragon!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                finish();
                break;
            case ReceiptModel.VALIDATION_WRONG_RECEIPT_DATA_OF_PURCHASE:
                Snackbar.make(editTextCena , "Błędny format daty!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case ReceiptModel.VALIDATION_WRONG_RECEIPT_NAME:
                Snackbar.make(editTextCena , "Nazwa paragonu nie może być pusta!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case ReceiptModel.VALIDATION_WRONG_RECEIPT_PRICE:
                Snackbar.make(editTextCena , "Błędny format ceny!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            case ReceiptModel.VALIDATION_WRONG_RECEIPT_NOTES:
                Snackbar.make(editTextCena , "Opis paragonu nie może być pusty!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                break;
            default:
                Toast.makeText(this, "DEFAULT" +
                        "", Toast.LENGTH_SHORT).show();
        }
//        Toast.makeText(this, "Tutaj trzeba będzie validować i do tego dodać do bazy danych", Toast.LENGTH_SHORT).show();
    }

    boolean neverUse = false;
    boolean temporaryNeverUse = true;
    public Dialog onCreateInformationDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_addreceipt_information);

        Button btnDalej = dialog.findViewById(R.id.button_next_alert_dialog_information);
        CheckBox checkBoxDecition = dialog.findViewById(R.id.checkButtonInformation);
        ImageButton btnExit = dialog.findViewById(R.id.button_escape_information);

        btnExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        checkBoxDecition.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                temporaryNeverUse = isChecked;
            }
        });

        btnDalej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                zapisz wyniki do bazy danych
                neverUse = temporaryNeverUse;
                doAddStuff();
                dialog.dismiss();

            }
        });
        return dialog;
    }

    private void declarate() {
        DataZakupu();
        ParagonNazwa();
        Cena();
        editTextNazwaSklepu = findViewById(R.id.SklepEditTekst);
        Kategoria();
        Opis();
        Tags();
        Produkty();
        Gwarancja();
        Zdjecia();
    }

    //    Data Zakupów
    private void DataZakupu() {

        editTekstDataZakupow = findViewById(R.id.dataZakupyEditText);
        buttonCalendar = findViewById(R.id.dataZakupuCalendar);

        editTekstDataZakupow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar();
            }
        });

        buttonCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAndFindDateWithCalendar();
            }
        });

        editTekstDataZakupow.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                receipt.setReceipt_date_of_purchase(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showAndFindDateWithCalendar(){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);

        DatePickerDialog picker = new DatePickerDialog(AddReceipt.this,
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
                        editTekstDataZakupow.setText(date);
                        receipt.setReceipt_date_of_purchase(date);
                    }
                }, year, month, day);
        picker.show();
    }

    //    Paragon Nazwa
    private void ParagonNazwa() {
        editTextParagonNazwa = findViewById(R.id.ParagonNazwaEditTekst);
        textParagonNazwaIlosc = findViewById(R.id.xParagonTekst);

        setTextSize(textParagonNazwaIlosc, "", 50);

        editTextParagonNazwa.addTextChangedListener(new TextWatcher() {
            String rememberedText = "" ;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rememberedText = s.toString();
            }

            String name = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(!rememberedText.equals(s.toString())){
                    if(editTextParagonNazwa.getText().toString().length() == 0)
                        editTextParagonNazwa.setText("0");
                    else{
                        if(checkSize(editTextParagonNazwa.getText().toString().length(), 50)){
                            name = editTextParagonNazwa.getText().toString()+" ";
                            textParagonNazwaIlosc.setText(editTextParagonNazwa.getText().toString().length()+" ");
                            receipt.setReceipt_name(name);
                        }
                        else{
                            editTextParagonNazwa.setText(rememberedText);
                            name = rememberedText;
                            Snackbar.make(editTextCena , "Za dużo znaków!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }
                        receipt.setReceipt_name(name);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setTextSize(TextView v, String rememberedText, int limit){
        if(v.getText().toString().length() == 0)
            v.setText("0");
        else{
            if(checkSize(editTextParagonNazwa.getText().toString().length(), limit))
                v.setText(v.getText().toString().length()+" ");
            else{
                v.setText(rememberedText);
                Snackbar.make(editTextCena , "Za dużo znaków!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
            }

        }
    }

    private boolean checkSize(int size, int limit){
        if(size <= limit)
            return true;
        else
            return false;
     }

     //    Cena
     private void Cena() {
        editTextCena = findViewById(R.id.CenaEditTekst);

        editTextCena.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty())
                    if(!checkGreaterThanZeroTekst(s.toString())){
                        Snackbar.make(editTextCena , "Cena musi być większa od zera!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        editTextCena.setText("");
                    }
            }
        });
     }

    private boolean checkGreaterThanZeroTekst(String cena){
        if(Double.parseDouble(cena) >= 0.0)
            return true;
        else
            return false;
    }

    //    Kategoria
    private final String[] paths = {"Ciuchy", "Kuchnia", "Śmieci"};
    private void Kategoria() {
        spinnerKategoria = findViewById(R.id.KategoriaSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddReceipt.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKategoria.setAdapter(adapter);

        spinnerKategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //    Opis
    private void Opis() {

        editTextOpis = findViewById(R.id.OpisEditTekst);
        textOpisIlosc = findViewById(R.id.xOpis);

        setTextSize(textOpisIlosc, "", 500);

        editTextOpis.addTextChangedListener(new TextWatcher() {
            String rememberedText = "" ;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rememberedText = s.toString();
            }

            String notes = "";
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!rememberedText.equals(s.toString())){
                    if(editTextOpis.getText().toString().length() == 0)
                        textOpisIlosc.setText("0");
                    else{
                        if(checkSize(editTextOpis.getText().toString().length(), 50)){
                            notes = editTextOpis.getText().toString().length()+" ";
                            textOpisIlosc.setText(notes);

                            receipt.setReceipt_notes(notes);
                        }
                        else{

                            editTextParagonNazwa.setText(rememberedText);
                            receipt.setReceipt_notes(rememberedText);
                            Snackbar.make(editTextCena , "Za dużo znaków!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    //    Znaczniki
    private void Tags() {

        editTextTag = findViewById(R.id.ZnacznikEditTekst);
        buttonAddTag = findViewById(R.id.ZnacznikDodajButton);;
        textTagIlosc = findViewById(R.id.xTekstZnacznik);
        textTags = findViewById(R.id.TekstSaveZnaczniki);

        editTextTag.addTextChangedListener(new TextWatcher() {
            String rememberedText = "";
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                rememberedText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!rememberedText.equals(s.toString())){
                    if(editTextTag.getText().toString().length() == 0)
                        textOpisIlosc.setText("0");
                    else{
                        if(editTextTag.getText().toString().length() < 15){
                            // Nothing
                        }
                        else{
                            editTextTag.setText(rememberedText);
                            Snackbar.make(editTextCena , "Za dużo znaków!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                        }

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        textTagIlosc.setText("0");

        buttonAddTag.setOnClickListener(new View.OnClickListener() {
            int ilosc = 0;
            @Override
            public void onClick(View v) {
                if(ilosc < 10){
                    String newTag = editTextTag.getText().toString();

                    if(!newTag.equals("")){
                        textTags.setText(textTags.getText().toString() + " #" + editTextTag.getText().toString() + " ");

                        ilosc++;
                        textTagIlosc.setText(ilosc + "");
                    }
                    else
                        Snackbar.make(editTextCena , "Tag nie może być pusty!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

                }
                else
                    Snackbar.make(editTextCena , "Nie możesz więcej Tagów!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();

            }
        });
    }

    //    Produkty
    private static final ArrayList<String> products = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private void Produkty() {
        products.add("Telewizor");
        products.add("Skarpetki");
        products.add("Monitor");
        products.add("Kiełbasa");

        spinnerProduct = findViewById(R.id.produktySpinner);
        spinnerProduct.setSelected(false);
        adapter = new ArrayAdapter<>(AddReceipt.this,
                android.R.layout.simple_spinner_item, products);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProduct.setAdapter(adapter);
        spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int ilosc = 0;
            boolean first = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(first){
                    first = false;
                }
                else{
                    if(ilosc < 10){
                        textProducs.setText(textProducs.getText().toString()+" "+products.get(position));

                        ilosc++;
                        textProductIlosc.setText(ilosc+"");
                    }
                    else{
                        Snackbar.make(editTextCena , "Za dużo Produktów!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonProduct = findViewById(R.id.DodajProduktButton);
        textProductIlosc = findViewById(R.id.xTekstProdukt);
        textProductIlosc.setText("0");

        textProducs = findViewById(R.id.tekstProdukty);

        buttonProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateAddProductDialog().show();
            }
        });
    }

    public Dialog onCreateAddProductDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_quick_product);

        Button btnStworz = dialog.findViewById(R.id.button_q_product_stworz);
        Button btnCofnij = dialog.findViewById(R.id.button_q_product_cofnij);
        ImageButton btnExit = dialog.findViewById(R.id.button_escape_quick_product);

        ImageButton btnAparat = dialog.findViewById(R.id.button_q_product_aparat);
        ImageButton btnGaleria = dialog.findViewById(R.id.button_q_product_galeria);
        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Wybierz zdjęcie");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 20);
            }
        });

        final EditText nazwaProduktu = dialog.findViewById(R.id.editText_q_productNazwa);

        imageProduct = dialog.findViewById(R.id.imageViewZdjecieBoot);

        btnAparat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);

            }
        });

        btnStworz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(!nazwaProduktu.getText().toString().equals("")){
                    products.add(nazwaProduktu.getText().toString());
                    adapter.notifyDataSetChanged();

                    dialog.dismiss();
                }
                else{
                    Snackbar.make(editTextCena , "Nazwa nie może być pusta!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }

            }
        } );

        btnCofnij.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return dialog;
    }

    //    Gwarancja
    private void Gwarancja() {
        seekBarGwarancja = findViewById(R.id.gwarancjaSlider);
        seekBarGwarancja.setMax(650);

        textGwarancjaIlosc = findViewById(R.id.gwarancjaTekst);
        textGwarancjaIlosc.setText("0 dni");

        seekBarGwarancja.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress <= 30){
                    textGwarancjaIlosc.setText(progress + " dni");
                }else if(progress < 60){
                    textGwarancjaIlosc.setText("1 miesiąc");
                }else if(progress < 340){
                    textGwarancjaIlosc.setText(progress/30 + " miesiące");
                }else if(progress < 600){
                    textGwarancjaIlosc.setText("rok");
                }else{
                    textGwarancjaIlosc.setText("2 lata");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    //    Zdjecia
    ArrayList<Bitmap> bitmapList = new ArrayList<>();
    CostumAdapter adapterC;
    private void Zdjecia() {
        buttonCamera = findViewById(R.id.aparatButton);
        buttonGaleria = findViewById(R.id.GaleriaButton);

        listViewZdjecia = findViewById(R.id.contenerZdjeciaAddR);

        adapterC = new CostumAdapter(this, bitmapList);
//        listViewZdjecia.setAdapter(adapterC);

        buttonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        buttonGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
                getIntent.setType("image/*");

                Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");

                Intent chooserIntent = Intent.createChooser(getIntent, "Wybierz zdjęcie");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});

                startActivityForResult(chooserIntent, 1);
            }
        });
    }
    Bitmap bitmap;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            bitmapList.add(bitmap);


//            ((ListAdapter) adapterC).notifyDataSetChanged();

        }else if(requestCode == 1){
//            Uri selectedImage = data.getData();
//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();

//            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//            .setImageBitmap(bitmap);

//            if (bitmap != null) {
//                ImageView rotate = (ImageView) findViewById(R.id.rotate);
//
//            }
        }else if(requestCode == 10){
            if(resultCode == RESULT_OK){
                bitmap = (Bitmap) data.getExtras().get("data");
                imageProduct.setImageBitmap(bitmap);
            }

        }else if(requestCode == 20){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            imageProduct.setImageBitmap(bitmap);
        }

    }
}
