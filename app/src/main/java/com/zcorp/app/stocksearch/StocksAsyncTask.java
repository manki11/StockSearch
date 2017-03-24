package com.zcorp.app.stocksearch;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Mankirat on 12-03-2017.
 */

public class StocksAsyncTask extends AsyncTask<String, Void, ArrayList<Stocks>> {

    StocksDownloadListener listener;

    StocksAsyncTask() {

    }

    void setStocksDownloadListener(StocksDownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected ArrayList<Stocks> doInBackground(String... params) {
        String urlString=params[0];
        StringBuffer stringBuffer=new StringBuffer();

        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
                return null;
            }

//            String result = "";
            Scanner s = new Scanner(inputStream);
            while (s.hasNext()) {
//                result = result + s.nextLine();
                stringBuffer.append(s.nextLine());
            }
            Log.i("CourseData", stringBuffer.toString());

        } catch (MalformedURLException e) {
            return null;
        } catch (IOException e) {

        }

        return stockCourceList(stringBuffer.toString());
    }

    private ArrayList<Stocks> stockCourceList(String json) {

        try{
            JSONObject object=new JSONObject(json);
            JSONObject query=object.getJSONObject("query");
            JSONObject results=query.getJSONObject("results");
            JSONArray quote=results.getJSONArray("quote");
            ArrayList<Stocks> stockList=new ArrayList<>();

            for(int i=0;i<quote.length();i++){
                JSONObject stockObject=quote.getJSONObject(i);
                String name=stockObject.getString("Name");
                String sticker=stockObject.getString("symbol");
                String change=stockObject.getString("Change");
                String currency=stockObject.getString("Currency");

                Log.i("TAG","name"+name);

                Stocks s=new Stocks(name,sticker,change,currency);
                stockList.add(s);

            }
            return stockList;

        }catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public interface StocksDownloadListener {
        void onDownloadComplete(ArrayList<Stocks> courses);
    }

    @Override
    protected void onPostExecute(ArrayList<Stocks> stockses) {
        super.onPostExecute(stockses);
        if (listener != null)
            listener.onDownloadComplete(stockses);
    }
}
