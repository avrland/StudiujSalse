package kowoof.studiujsalse;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends DrawerActivity {

    //We create here stuff for listView
    private ArrayList<String> listViewTitle = new ArrayList<>();
    private ArrayList<String> videoAvaible = new ArrayList<>();
    private ListView list;
    private wallet_list_create adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    clearWalletsList();
            }
        });
        fillListView();
        goToFigureDescriptionHandler();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_figures_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.onlyRueda:
                if(!item.isChecked()){
                    item.setChecked(true);
                    //pokażFiguryZRuedy();
                }
                else {
                    item.setChecked(false);
                    //pokażWszystkieFigury();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillListView(){
        list = findViewById(R.id.mainListView);
        listViewTitle.add("AL CENTRO");
        videoAvaible.add("0");
        listViewTitle.add("ARRIBA");
        videoAvaible.add("1");

        listViewTitle.add("ABAJO");
        videoAvaible.add("1");
        listViewTitle.add("LA CHICA");
        videoAvaible.add("1");
        listViewTitle.add("EL CHICO");
        videoAvaible.add("0");
        listViewTitle.add("AL CENTRO");
        videoAvaible.add("1");
        listViewTitle.add("ARRIBA");
        videoAvaible.add("0");
        listViewTitle.add("ABAJO");
        videoAvaible.add("1");
        listViewTitle.add("EL CHICO");
        videoAvaible.add("0");
        listViewTitle.add("AL CENTRO");
        videoAvaible.add("1");
        listViewTitle.add("ARRIBA");
        videoAvaible.add("0");
        listViewTitle.add("ABAJO");
        videoAvaible.add("0");


        adapter = new wallet_list_create(this, listViewTitle, videoAvaible);
        list.setAdapter(adapter);
    }
    private void clearWalletsList(){
        list = findViewById(R.id.mainListView);
        adapter = new wallet_list_create(MainActivity.this, listViewTitle, videoAvaible);
        listViewTitle.clear();
        videoAvaible.clear();
        list.setAdapter(adapter);
    }

    private void goToFigureDescriptionHandler(){
        //Go to specific wallet view when you click on stuff on list
        list = findViewById(R.id.mainListView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(position==0) showFigureDescription1();
                if(position==1) showFigureDescription2();
                if(position>1) showFigureDescription3();
            }
        });
    }
    private void showFigureDescription1(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("AL CENTRO");
        dialogBuilder.setMessage("Panowie ustawiając się lewym ramieniem do środka koła tworzą ruedę tańcząc krok podstawowy, również w trakcie trwania ruedy na tą komendę wracają do tego ustawienia.");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void showFigureDescription2(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("ARRIBA");
        dialogBuilder.setMessage("Partner porusza się do przodu prowadząc tym samym partnerkę do tyłu.");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }
    private void showFigureDescription3(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Pusto :(");
        dialogBuilder.setMessage("Wincyj wkrótce.");
        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }


}
