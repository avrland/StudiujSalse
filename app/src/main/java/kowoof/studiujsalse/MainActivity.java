package kowoof.studiujsalse;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends DrawerActivity {

    //We create here stuff for listView
    private ArrayList<String> listViewTitle = new ArrayList<>();
    private ArrayList<String> videoAvaible = new ArrayList<>();
    private ListView list;
    private wallet_list_create adapter;
    private String figuresList[] = new String[100];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearListView();
            }
        });
        fillListView_AllFigures();
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
                    fillListView_RuedaOnly();
                }
                else {
                    item.setChecked(false);
                    fillListView_AllFigures();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fillListView_AllFigures(){
        clearListView();
        list = findViewById(R.id.mainListView);
        try {
            JSONArray new_array = new JSONArray(readJSON());
            for (int i = 0, count = new_array.length(); i < count; i++) {
                try {
                    JSONObject jsonObject = new_array.getJSONObject(i);
                    listViewTitle.add(jsonObject.getString("nazwa"));
                    videoAvaible.add(jsonObject.getString("wideo"));
                    figuresList[i] = jsonObject.getString("nazwa");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter = new wallet_list_create(MainActivity.this, listViewTitle, videoAvaible);
            list.setAdapter(adapter);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void fillListView_RuedaOnly(){
        clearListView();
        list = findViewById(R.id.mainListView);
        try {
            JSONArray new_array = new JSONArray(readJSON());
            for (int i = 0, count = new_array.length(); i < count; i++) {
                try {
                    JSONObject jsonObject = new_array.getJSONObject(i);
                    if(jsonObject.getString("rueda").equals("1")) {
                        listViewTitle.add(jsonObject.getString("nazwa"));
                        videoAvaible.add(jsonObject.getString("wideo"));
                        figuresList[i] = jsonObject.getString("nazwa");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter = new wallet_list_create(MainActivity.this, listViewTitle, videoAvaible);
            list.setAdapter(adapter);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void clearListView(){
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
                findFigure(position);
            }
        });
    }

    private void findFigure(int position){
        String searchedFigure = figuresList[position];
        try {
            JSONArray new_array = new JSONArray(readJSON());

            for (int i = 0, count = new_array.length(); i < count; i++) {
                try {
                    JSONObject jsonObject = new_array.getJSONObject(i);
                    if(jsonObject.getString("nazwa").equals(searchedFigure)){
                           String currentName = jsonObject.getString("nazwa");
                           String currentDescription = jsonObject.getString("opis");
                           String currentVideo = jsonObject.getString("wideo");
                           buildAlertDialog(currentName, currentDescription, currentVideo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void buildAlertDialog(String name, String description, final String video){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
        dialogBuilder.setTitle(name);
        dialogBuilder.setMessage(description);
        if(!video.equals("0")) {
            dialogBuilder.setPositiveButton("Wideo", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    watchYoutubeVideo(getApplicationContext(), video);
                }
            });
        }
        AlertDialog alert = dialogBuilder.create();
        alert.show();
    }
    public static void watchYoutubeVideo(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }
    private String readJSON(){ //odczyt bazy danych figur z pliku
        AssetManager assetManager = getAssets();
        InputStream input;
        String text = "";
        try {
            input = assetManager.open("figurebase.json");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();
            // byte buffer into a string
            text = new String(buffer);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return text;
    }
}
