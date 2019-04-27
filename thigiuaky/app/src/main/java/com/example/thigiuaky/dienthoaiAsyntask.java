package com.example.thigiuaky;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class dienthoaiAsyntask extends AsyncTask<String,Void, JSONObject> {
    private Iview iview;
    private Map<String,String> mMap;

    public dienthoaiAsyntask(Map<String, String> mMap) {

        this.mMap = mMap;
    }

    public dienthoaiAsyntask(Iview mview, Map nMap) {
        this.iview = mview;
        this.mMap = nMap;
    }

    public dienthoaiAsyntask(Iview iview) {
        this.iview = iview;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type","application/json");
            connection.setRequestMethod("POST");
//add params
            JSONObject parentjson = new JSONObject();
            Iterator iterator = mMap.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String value = mMap.get(key);
                parentjson.put(key,value);
            }

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            bufferedWriter.write(String.valueOf(parentjson));
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
//

            connection.connect();


            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String result;
            while ((result = bufferedReader.readLine()) != null){
                stringBuffer.append(result);
            }
            result  = stringBuffer.toString();
            JSONObject parentObject = new JSONObject(result);
            return parentObject;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        try {

            int results = jsonObject.getInt("result");
            if (results > 0)
            {
                JSONArray jsonArray = jsonObject.getJSONArray("response_data");
                iview.getdatasuccess(jsonArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
