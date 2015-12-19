package com.example.omegasupreme.sampleuilayout;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;

public class RecordDataLowerFragment extends Fragment {

    View view;
    Button updateNow;
    BlueTooth blue = new BlueTooth();

    public void onViewDataClick(View view){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record_device_l,container, false);

        blue.StartThread();

        updateNow = (Button)view.findViewById(R.id.btnUpdateNow);

        updateNow.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Updating...", Toast.LENGTH_LONG).show();

                blue.callDevice();
            }
        });


        BlueTooth blue = new BlueTooth();
        blue.StartThread();
        //blue.setSelected_device("");

        return view;
    }


}