package com.shruti.mycompareapp;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

//called when searchButton is clicked
public class Display extends ListActivity
{
    private SharedPreferences savedSearches; //Saves the winner result
    private ArrayList<String> tags; // list of tags for saved searches
    private ArrayList<String> values; // list of values for saved searches
    private ArrayList<String> resultant; // list of resultant for saved searches
    private ArrayAdapter<String> adapter;// binds tags to ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        //calling intent
        Intent intent = getIntent();

        //Storing winner string name and result into variables
        String winnerName = intent.getStringExtra("winnerName");
        String winnerResult = intent.getStringExtra("winnerResult");


        //Extracting values from saved searches
        savedSearches = PreferenceManager.getDefaultSharedPreferences(this);
        tags = new ArrayList<String>(savedSearches.getAll().keySet());
        values = new ArrayList<String>();
        resultant = new ArrayList<String>();
        for(int i=0; i < tags.size(); i++)
        {
            values.add(i, savedSearches.getString(tags.get(i), ""));
        }

        for(int i=0; i < tags.size(); i++)
        {
            resultant.add(i, tags.get(i) + "                                    " + values.get(i));
        }
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, resultant);
        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
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
}
