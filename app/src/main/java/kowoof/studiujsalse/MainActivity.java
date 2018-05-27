package kowoof.studiujsalse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends DrawerActivity {

    //We create here stuff for listView
    private ArrayList<String> listViewTitle = new ArrayList<>();
    private ArrayList<String> listViewSubTitle = new ArrayList<>();
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
                Snackbar.make(view, "Wyszukaj figurę", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });
        fillListView();
        goToFigureDescriptionHandler();
    }
    private void fillListView(){
        list = findViewById(R.id.mainListView);
        listViewTitle.add("AL CENTRO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ARRIBA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ABAJO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("LA CHICA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("EL CHICO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("AL CENTRO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ARRIBA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ABAJO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("LA CHICA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("EL CHICO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("AL CENTRO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ARRIBA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("ABAJO");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("LA CHICA");
        listViewSubTitle.add("Opis...");
        listViewTitle.add("EL CHICO");
        listViewSubTitle.add("Opis...");
        adapter = new wallet_list_create(this, listViewTitle, listViewSubTitle);
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
