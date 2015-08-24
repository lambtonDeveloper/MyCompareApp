package com.shruti.mycompareapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;


public class MainActivity extends ActionBarActivity
{

    // called when MainActivity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to the EditTexts
        final EditText text1 = (EditText) findViewById(R.id.text1);
        final EditText text2 = (EditText) findViewById(R.id.text2);

        // get references to the Button
        Button button = (Button) findViewById(R.id.button);

        // create a new AlertDialog Builder and Bkactivity
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt1 = ((EditText) findViewById(R.id.text1)).getText().toString();
                String txt2 = ((EditText) findViewById(R.id.text2)).getText().toString();
                Log.d("txt1 & txt2", txt1 + "&" + txt2);
                if (txt1.length() > 0 && txt2.length() > 0)
                {
                    new Bkactivity(MainActivity.this).execute(txt1, txt2);
                    text1.setText("");
                    text2.setText("");
                }
                else
                {
                    alertDialog.setTitle("ERROR");
                    alertDialog.setMessage("Please enter both strings");
                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alertDialog.show();

                }
            }

        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }






}
