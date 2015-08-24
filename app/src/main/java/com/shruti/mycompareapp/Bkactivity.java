package com.shruti.mycompareapp;

/**
 * Created by macadmin on 2015-07-23.
 */
/**
 * Created by macadmin on 2015-07-23.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


// Runs in background when listens to setOnClickListener
public class Bkactivity extends AsyncTask<String, Void, Void>
{

    Context ctx;

    Bkactivity(Context ctx) {
        this.ctx = ctx;
    }

    String content, content2;

    @Override
    protected Void doInBackground(String... params)
    {
        //declarations of URL and bufferReader variables
        URL url, url2;
        BufferedReader br = null;
        BufferedReader br2 = null;

        // get the default SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctx);

        try
        {
            //setting connection and getting response from https
            //https://www.googleapis.com/customsearch/v1?key=INSERT_YOUR_API_KEY&cx=017576662512468239146:omuauf_lfve&q=lectures
            url = new URL("https://www.googleapis.com/customsearch/v1?key="+ URLEncoder.encode("AIzaSyA36XE1a538qQSz8a4iqiGC3YfYRXdbhLk")+"&cx="+URLEncoder.encode("011099672309493755658:kux034o4u9s")+"&q="+ URLEncoder.encode(params[0]));
            url2 = new URL("https://www.googleapis.com/customsearch/v1?key="+ URLEncoder.encode("AIzaSyA36XE1a538qQSz8a4iqiGC3YfYRXdbhLk")+"&cx="+URLEncoder.encode("011099672309493755658:kux034o4u9s")+"&q="+ URLEncoder.encode(params[1]));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int respCode = connection.getResponseCode();
            Log.d("respCode", respCode + "");
            HttpURLConnection connection2 = (HttpURLConnection) url2.openConnection();
            int respCode2 = connection2.getResponseCode();
            Log.d("respCode2", respCode2 + "");

            br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            Log.d("br1", String.valueOf(br));
            br2 = new BufferedReader(new InputStreamReader((connection2.getInputStream())));
            Log.d("br2", String.valueOf(br2));

            //create new stringBuider
            StringBuilder strbul = new StringBuilder();
            StringBuilder strbul2 = new StringBuilder();

            //Reading and extracting value of EditText1
            String line;
            while ((line = br.readLine()) != null)
            {
                strbul.append(line);
            }
            content = strbul.toString();
            Log.d("content", content.toString());

            JSONObject jsonObject = new JSONObject(content);
            Log.d("jsonCnt", jsonObject.toString());
            JSONObject jsonObject1 = jsonObject.getJSONObject("queries");
            Log.d("jsonQur", jsonObject1.toString());
            JSONArray jsonArray = jsonObject1.getJSONArray("request");
            Log.d("ResArray", jsonArray.toString());

            JSONObject jArr= jsonArray.getJSONObject(0);
            Log.d("jArr", jArr.toString());
            String str = jArr.getString("totalResults");
            Log.d("totRes", str.toString());

           //Reading and extracting value of EditText2
            String line2;
            while ((line2 = br2.readLine()) != null)
            {
                strbul2.append(line2);
            }
            content2 = strbul2.toString();
            Log.d("content2", content2.toString());

            JSONObject jsonObj = new JSONObject(content2);
            Log.d("jsonCnt2", jsonObj.toString());
            JSONObject jsonObj2 = jsonObj.getJSONObject("queries");
            Log.d("jsonQur2", jsonObj2.toString());
            JSONArray jsonArr2 = jsonObj2.getJSONArray("request");
            Log.d("ResArray2", jsonArr2.toString());

            JSONObject jArr2= jsonArr2.getJSONObject(0);
            Log.d("jArr2", jArr2.toString());
            String str2 = jArr2.getString("totalResults");
            Log.d("totRes2", str2.toString());

            //converting integer variables to string
            int Res = Integer.parseInt(str);
            int Res2 = Integer.parseInt(str2);

            //comparing results of EditText1 and EditText2, and storing in sharedPreferences
            if(Res > Res2)
            {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(params[0], String.valueOf(Res));
                editor.apply();
                Intent intent = new Intent(ctx, com.shruti.mycompareapp.Display.class);
                intent.putExtra("winnerName", params[0]);
                intent.putExtra("winnerResult", String.valueOf(Res));
                ctx.startActivity(intent);
            }
            else
            {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(params[1], String.valueOf(Res2));
                editor.apply();
                Intent intent = new Intent(ctx, com.shruti.mycompareapp.Display.class);
                intent.putExtra("winnerName", params[1]);
                intent.putExtra("winnerResult", String.valueOf(Res2));
                ctx.startActivity(intent);
            }



        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}
