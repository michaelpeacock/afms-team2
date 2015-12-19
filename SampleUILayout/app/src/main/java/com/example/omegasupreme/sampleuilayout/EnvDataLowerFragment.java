package com.example.omegasupreme.sampleuilayout;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

/**
 * Created by OmegaSupreme on 12/19/2015.
 */
public class EnvDataLowerFragment extends ListFragment {

    View view;
    EditText txtTime;
    EditText txtLat;
    EditText txtLong;
    EditText txtAlt;
    EditText txtTrueCompassDir;
    EditText txtWindSpeed;
    EditText txtCrossWind;
    EditText txtHeadWind;
    EditText txtTemp;
    EditText txtWindChill;
    EditText txtRelHum;
    EditText txtHeatIndex;
    EditText txtDewPoint;
    EditText txtBarPressure;
    EditText txtAbPressure;
    EditText txtDensityAltitude;

    public void onViewDataClick(View view){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.env_data_lower_fragment,container, false);


        // replace with data from point selected above
        String[] items = new String[] {};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, items);

        setListAdapter(adapter);


        return view;
    }


}
