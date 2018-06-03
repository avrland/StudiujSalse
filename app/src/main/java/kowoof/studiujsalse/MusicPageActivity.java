package kowoof.studiujsalse;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MusicPageActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        setDrawer();
    }

    private void setDrawer(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        //we add it the same stuff as in DrawerActivity because it's getting overwritten and hamburger button doesn't works
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    public void salsaSpotify(View view) {
        Toast.makeText(MusicPageActivity.this, "Salsa Spotify",
                Toast.LENGTH_LONG).show();
    }
    public void reggaetonSpotify(View view) {
        Toast.makeText(MusicPageActivity.this, "Reggaeton Spotify",
                Toast.LENGTH_LONG).show();
    }

    public void salsaTidal(View view) {
        Toast.makeText(MusicPageActivity.this, "Salsa Tidal",
                Toast.LENGTH_LONG).show();
    }

    public void reggaetonTidal(View view) {
        Toast.makeText(MusicPageActivity.this, "Reggaeton Tidal",
                Toast.LENGTH_LONG).show();
    }
}
