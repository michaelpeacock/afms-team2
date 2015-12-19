package com.example.omegasupreme.sampleuilayout;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by OmegaSupreme on 12/18/2015.
 */
public class RecordDataManualActivity  extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
  //      String value = intent.getStringExtra("key"); //if it's a string you stored.
        setContentView(R.layout.record_data_manual);


        String[] items = new String[] {"All Environment From", "Item 2", "Item 3"};
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, items));
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
       // Toast.makeText(this, "Clicked row " + position, Toast.LENGTH_SHORT).show();

        // need to find a better index method
        if(position == 0){
            Intent myIntent = new Intent(this, ManualDataFrom.class);
            //myIntent.putExtra("key", value); //Optional parameters
            this.startActivity(myIntent);
        }

    }
}
