package com.example.omegasupreme.sampleuilayout;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */
public class ServerDataAccess {

    public ServerDataAccess(){

    }

    public <E> List<E> getObjectList(String jsonString, Class<E> classT){
        Gson gson = new Gson();

        // jsonString = jsonString.replaceAll("null", "");

        Log.d("", "");

        Type type = new TypeToken<List<E>>(){}.getType();
        List<Map> tmpList = gson.fromJson(jsonString, type);
        List<E> resultList = new ArrayList<E>(tmpList.size());
        for(Map map: tmpList){
            String tmpJson = gson.toJson(map);
            resultList.add(gson.fromJson(tmpJson, classT));
        }
        return resultList;
    }


    public String getJSON(String serverUrl){
        try {
            URL url = new URL(serverUrl);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            String rv = "";
            while((tmp=reader.readLine())!=null) {
                json.append(tmp).append("\n");
                rv += tmp;
                Log.d("getJSON", tmp);
            }
            reader.close();

            return rv;
        }catch(Exception e){
            Log.d("getJSON Exception",e.toString());
            return null;
        }
    }

    public void sendJson(String urlStr, String json) {

        // test url
        urlStr = "http://skyenet.duckdns.org:8080/restwebserver/database/postenvdata";


        String string = "";
        try {

            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0;  i < jsonArray.length(); i++){
                JSONObject jsonObject  = jsonArray.getJSONObject(i);


              //  JSONObject jsonObject = new JSONObject(json);
                System.out.println(jsonObject);

                // Step2: Now pass JSON File Data to REST Service
                try {
                    URL url = new URL("http://skyenet.duckdns.org:8080/restwebserver/database/postenvdata");
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    out.write(jsonObject.toString());
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while (in.readLine() != null) {
                    }
                    System.out.println("\nCrunchify REST Service Invoked Successfully..");
                    in.close();
                } catch (Exception e) {
                    System.out.println("\nError while calling Crunchify REST Service");
                    System.out.println(e);
                }
            }
            //br.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR", e.toString());
        }

    }

    public void sendJson(String urlStr, JSONObject json) {

        // test url
        urlStr = "http://skyenet.duckdns.org:8080/restwebserver/database/postenvdata";


        String string = "";
        try {


                JSONObject jsonObject  = json;


                //  JSONObject jsonObject = new JSONObject(json);
                System.out.println(jsonObject);

                // Step2: Now pass JSON File Data to REST Service
                try {
                    URL url = new URL("http://skyenet.duckdns.org:8080/restwebserver/database/postenvdata");
                    URLConnection connection = url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                    System.out.println("WM ---- " + jsonObject.toString());
                    out.write(jsonObject.toString());
                    System.out.println("WM ---- crash?");
                    out.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    while (in.readLine() != null) {
                    }
                    System.out.println("\nCrunchify REST Service Invoked Successfully..");
                    in.close();
                } catch (Exception e) {
                    System.out.println("\nError while calling Crunchify REST Service");
                    System.out.println(e);
                }
            //br.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ERROR", e.toString());
        }

    }


    public String createUser(){
        String urlStr = "http://skyenet.duckdns.org:8080/restwebserver/database/pushenvdata?SC=test";
        try{
            URL url = new URL(urlStr);
            HttpURLConnection connection =
                    (HttpURLConnection)url.openConnection();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String tmp2="";
            String rv = "";
            while((tmp2=reader.readLine())!=null) {
                json.append(tmp2).append("\n");
                rv += tmp2;
                Log.d("getJSON",tmp2);
            }
            reader.close();


            // JSONObject data = new JSONObject(json.toString());

            // This value will be 404 if the request was not
            // successful
//            if(data.getInt("cod") != 200){
//                return null;
//            }

            return rv;
        }
        catch (Exception e){
            Log.e("createUser", e.toString());
            return null;
        }
    }

}
