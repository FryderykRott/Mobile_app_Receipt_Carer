package com.example.fryderyk.receiptcarer;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.fryderyk.receiptcarer.Utils.Constants;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_add_black_40dp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = onCreateAddReceiptDialog();
                dialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Sztuczne logowanie
        logIn();
    }

    private void logIn() {
        //Ustawienie zalogowania i loginu na prawdziwy
        Constants.isloged = true;
        Constants.userID = 1;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add_receipt) {
            Dialog dialog = onCreateAddReceiptDialog();
            dialog.show();
        } else if (id == R.id.nav_create_diagram) {
            Dialog dialog = onCreateAddCategoriesDialog();
            dialog.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Dialog onCreateAddReceiptDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_skanuj);

        ImageButton btnSkanner = dialog.findViewById(R.id.buttonSkanuj);
        Button btnPomin = dialog.findViewById(R.id.buttonPomin);
        ImageButton btnExit = dialog.findViewById(R.id.button_escape_skanuj);

        btnSkanner.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                dialog.dismiss();
            }
        } );

        btnPomin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddReceipt.class);
                startActivity(intent);
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

    public Dialog onCreateAddCategoriesDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_choose_categories);

        Button btnNext = dialog.findViewById(R.id.button_next_alert_dialog_categories);
        CheckBox cbWszystkie = dialog.findViewById(R.id.checkButtonCategories);
        ImageButton btnExit = dialog.findViewById(R.id.button_escape_categories);


        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateDiagram.class);
                startActivity(intent);
                dialog.dismiss();
            }
        } );

        cbWszystkie.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do nothing
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

    public void clickItem(View view){
        LinearLayout ll = (LinearLayout) view;
        ll.setBackgroundColor(Color.parseColor("#EA9392"));
    }

    public Dialog onCreateScannerErrorDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.alert_dialog_error_scan);
        ImageButton btnNext = dialog.findViewById(R.id.button_next);
        ImageButton btnBack = dialog.findViewById(R.id.button_back);
        ImageButton btnExit = dialog.findViewById(R.id.button_escape);


        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddReceipt.class);
                startActivity(intent);
                dialog.dismiss();
            }
        } );

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
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

    boolean first_scan = true;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(first_scan){
            Dialog dialog = onCreateScannerErrorDialog();
            dialog.show();
            first_scan = false;
        }
        else{
            Intent intent = new Intent(getApplicationContext(), AddReceipt.class);
            intent.putExtras(data.getExtras());

            startActivity(intent);
        }

    }
}
