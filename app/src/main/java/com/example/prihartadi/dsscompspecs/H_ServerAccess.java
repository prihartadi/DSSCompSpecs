package com.example.prihartadi.dsscompspecs;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by prihartadi on 19/07/2017.
 */

public class H_ServerAccess {

    public void testURL(String addr) throws Exception {
        URL url = new URL(addr);
        try {
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.connect();
            Log.e("Logged",conn.getResponseCode()+"");
        }
        catch (Exception e){

            Log.e("Logged","ERROR"+e.toString());
        }
    }
    public Object getData(String server, Class c,String type){
        Gson gson = new Gson();
        Object response = gson.fromJson(getJSON(server,type),c);
        Log.v("LOG_getData",response.getClass().toString());
        return response;
    }

    public String getJSON(String server, String type) {
        HttpURLConnection c = null;
//        String addr = "http://"+server+"/comp/Services/"+type;
        String addr = "http://"+server+"/aaa/Services/"+type;
        try {
            //Log.v("LOG_getJSON", "GO");
            URL u = new URL(addr);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status = c.getResponseCode();
            //Log.v("LOG_getJSON", status+"");
            switch (status) {
                case 200:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                        Log.v("LOG_getJSON_line",line);
                    }
                    br.close();
                    return sb.toString();
                case 201:
                    BufferedReader brs = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sbs = new StringBuilder();
                    String lines;
                    while ((lines = brs.readLine()) != null) {
                        sbs.append(lines+"\n");
                    }
                    brs.close();
                    return sbs.toString();
            }

        } catch (Exception ex) {
            Log.e("LOG_getJSON","ERROR");
        }

        return null;
    }

}
