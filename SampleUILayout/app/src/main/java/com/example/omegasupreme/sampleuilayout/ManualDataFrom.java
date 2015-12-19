package com.example.omegasupreme.sampleuilayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */
public class ManualDataFrom extends Activity {
    EditText txtTime;
    EditText txtLat;
    EditText txtLong;
    EditText txtAlt;


    public void onCancelClick(View view){
        txtTime.setText("");
        txtLat.setText("");
        txtLong.setText("");
        txtAlt.setText("");


        View v = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    public void onSubmitClick(View view) {

        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("time", txtTime.getText().toString());
            jsonObject.put("latitude", txtLat.getText().toString());
            jsonObject.put("longitude", txtLong.getText().toString());
            jsonObject.put("altitude", txtAlt.getText().toString());


            new Thread(){
                public void run(){
                    ServerDataAccess dataAccess = new ServerDataAccess();
                    dataAccess.sendJson("",  "[" + jsonObject.toString() + "]");
                }
            }.start();


            View v = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_data_form);

        txtTime = (EditText) findViewById(R.id.txtTime);
        txtLat = (EditText) findViewById(R.id.txtLat);
        txtLong = (EditText) findViewById(R.id.txtLong);
        txtAlt = (EditText)findViewById(R.id.txtAlt);
    }


}
