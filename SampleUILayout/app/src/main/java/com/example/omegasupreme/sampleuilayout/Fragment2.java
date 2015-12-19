package com.example.omegasupreme.sampleuilayout;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

public class Fragment2 extends Fragment {

    View view;
    Button recordData;
    Button viewData;

    public void onViewDataClick(View view){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment2,container, false);

        recordData = (Button)view.findViewById(R.id.btnRecordEnvData);

        recordData.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getActivity(), recordData);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.record_device_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                      //  Toast.makeText(getActivity(), "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();

                        if(item.getTitle().equals("Manual")){
                            Intent myIntent = new Intent(getActivity(), RecordDataManualActivity.class);
                            //myIntent.putExtra("key", value); //Optional parameters
                            getActivity().startActivity(myIntent);
                        }
                        else if(item.getTitle().equals("Device")){
                            Intent myIntent = new Intent(getActivity(), DeviceList.class);
                            //myIntent.putExtra("key", value); //Optional parameters
                            getActivity().startActivity(myIntent);
                        }

                        return true;
                    }
                });

                popup.show();
            }
        });

        viewData   = (Button)view.findViewById(R.id.btnViewEnvData);
        viewData.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(getActivity(), EnvData.class);
//                myIntent.putExtra("key", value); //Optional parameters
                getActivity().startActivity(myIntent);
//                new Thread(){
//                    public void run(){
//                        ServerDataAccess dataAccess = new ServerDataAccess();
//
//
//                        String json =  dataAccess.getJSON("http://skyenet.duckdns.org:8080/restwebserver/database/getenvdata");
//                        dataAccess.sendJson("", json);
//                        List<Measurements> measurements = dataAccess.getObjectList(json, Measurements.class);
//                        dataAccess.createUser();
//                    }
//                }.start();

            }
        });

        return view;
    }


}