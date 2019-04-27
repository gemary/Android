package com.example.thigiuaky;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

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
import java.util.Map;

public class loginAsyntask extends AsyncTask<String,Boolean, JSONObject> {
    private Map<String,String> mMap;
    private LoginView loginView;
    private Context context;
    private ProgressDialog dialog;
   public static int result_id;
    public loginAsyntask(Map<String, String> mMap, LoginView loginView, Context context) {
        this.mMap = mMap;
        this.loginView = loginView;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Loading.....");

    }

    @Override
    protected void onProgressUpdate(Boolean... values) {
        if(values[0]  == true)
        {
            dialog.dismiss();
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        if (jsonObject != null)
        {
            try {
                int Result = jsonObject.getInt("result");
                String messeage = jsonObject.getString("response_message");
                JSONObject responseData = jsonObject.getJSONObject("response_data");

                result_id = responseData.getInt("user_id");


                if (Result < 0) {
                    loginView.LoginFail(messeage);
                } else {
                    loginView.Loginsuccess(messeage);
                }
            }
            catch(JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected JSONObject doInBackground(String... strings) {


        try {

            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();

            connection.setRequestProperty("Content-Type","application/json");

            connection.setRequestMethod("POST");

            // add paramas
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
            publishProgress(true);
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
}
