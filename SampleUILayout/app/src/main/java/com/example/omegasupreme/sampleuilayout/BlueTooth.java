package com.example.omegasupreme.sampleuilayout;

/**
 * Created by OmegaSupreme on 12/19/2015.
 */

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Vector;

public class BlueTooth extends AppCompatActivity{

    private final static int REQUEST_ENABLE_BT = 1;
    private final int BYTE_BUFFER_SIZE = 4096;



    private BluetoothDevice selected_device = null;

    public BlueTooth(){
    }

    public void StartThread(){
        new Thread(){
            public void run(){
                //Call on periodic
                while (true) {
                    if (null == selected_device) {
                        selected_device = initBlueTooth();
                    }

                    callDevice();

                    try {
                        Thread.sleep(600000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public void callDevice(){
        if (selected_device != null) {
            System.out.println("Attempting to read data...");
            Measurements measurement = communicateWithDevice(selected_device);

            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("time", measurement.getTime());
                jsonObject.put("latitude", measurement.getLatitude());
                jsonObject.put("longitude", measurement.getLongitude());
                jsonObject.put("altitude", measurement.getAltitude());
                jsonObject.put("wind_speed", measurement.getWind_speed());
                jsonObject.put("crosswind", measurement.getCrosswind());
                jsonObject.put("headwind", measurement.getHeadwind());
                jsonObject.put("temperature", measurement.getTemperature());
                jsonObject.put("wind_chill", measurement.getWind_chill());
                jsonObject.put("relative_humidity", measurement.getRelative_humidity());
                jsonObject.put("heat_index", measurement.getHeat_index());
                jsonObject.put("dew_points", measurement.getDew_points());
                jsonObject.put("wet_bulb_temperature", measurement.getWet_bulb_temperature());
                jsonObject.put("barometric_pressure", measurement.getBarometric_pressure());
                jsonObject.put("density_altitude", measurement.getDensity_altitude());


                new Thread(){
                    public void run(){
                        ServerDataAccess dataAccess = new ServerDataAccess();
                        dataAccess.sendJson("",  "[" + jsonObject.toString() + "]");
                    }
                }.start();
            }
            catch (Exception e) {
                Log.e("ERROR", e.toString());
            }

        } else {
            System.out.println("No device found");
        }
    }

    public Measurements communicateWithDevice (BluetoothDevice device) {
        Measurements measurement = null;

        try {
            ParcelUuid[] uuids = device.getUuids();
            BluetoothSocket socket = device.createRfcommSocketToServiceRecord(uuids[0].getUuid());

            socket.connect(); // Connect should not be on the main Activity thread
            //manageConnectedSocket(socket); // on separate thread

            System.out.println("Device connected");
            // MY_UUID is the app's UUID string, also used by the server code
            //BluetoothSocket tmp = selected_device.createRfcommSocketToServiceRecord("MY_UUID_STRING");

            InputStream istream = socket.getInputStream();
            InputStreamReader ireader = new InputStreamReader(istream);

            OutputStream ostream = socket.getOutputStream();
            System.out.println("Created in/out streams");

            /* Send the "P\r" command, this will cause the device to send us data once a second.
               Start a loop reading from the input stream
             */

            //KestrelCommands command = KestrelCommands.CONTINIOUSDATA;
            String commandString = new String("S\r");
            String log = new String();
            log = "Sending ";
            //log += commandString;
            log += " command to weather station";
            System.out.println(log);
            System.out.println("Sending command to weather station");
            byte[] pollcommand = commandString.getBytes();

            ostream.write(pollcommand);
            try {
                Thread.sleep(1000);
            } catch (java.lang.InterruptedException e) { };
            System.out.println("Command sent...");

            byte[] result= new byte[BYTE_BUFFER_SIZE];

            // LF = 10
            // CR = 13
            // End = CR/LF
            int size;
            System.out.println("Reading result...");
            Arrays.fill(result, (byte) 0);

            size = istream.read(result);

            if (size <= 0) {
                System.out.println("No data read");
                return null;
            } else {

                System.out.println("Read " + size + " bytes");

                Vector<String> filtered_string = getBufferStrings(result, size, 15);

                measurement = new Measurements();

                for (String aString : filtered_string) {
                    System.out.println(aString);
                }

                if (filtered_string.size() == 3) {
                    String[] arr = filtered_string.get(2).split(",");

                    measurement.setTime(Integer.parseInt(arr[0]));
                    measurement.setWind_speed(Double.parseDouble(arr[3]));
                    measurement.setCrosswind(Double.parseDouble(arr[4]));
                    measurement.setHeadwind(Double.parseDouble(arr[5]));
                    measurement.setTemperature(Double.parseDouble(arr[6]));
                    measurement.setWind_chill(Double.parseDouble(arr[7]));
                    measurement.setRelative_humidity(Double.parseDouble(arr[8]));
                    measurement.setHeat_index(Double.parseDouble(arr[9]));
                    measurement.setDew_points(Double.parseDouble(arr[10]));
                    measurement.setWet_bulb_temperature(Double.parseDouble(arr[11]));
                    measurement.setBarometric_pressure(Double.parseDouble(arr[12]));
                    measurement.setAltitude(Double.parseDouble(arr[13]));
                    measurement.setDensity_altitude(Double.parseDouble(arr[14]));
                }

            }
            try {
                Thread.sleep(1000);
            } catch (java.lang.InterruptedException e) { };

            socket.close();

        } catch (IOException e) { };

        return measurement;
    }

    private BluetoothDevice initBlueTooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice selected_device = null;

        if (mBluetoothAdapter == null) {
            Toast burned_toast = new Toast(this);
            burned_toast.setText("Device does not support Bluetooth!");
            burned_toast.show();
        } else {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }

            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
            // If there are paired devices
            if (pairedDevices.size() > 0) {
                // Loop through paired devices
                for (BluetoothDevice device : pairedDevices) {
                    System.out.println("WM --" + device.getName() + "(" + device.getAddress() + ")\n");
                    if (device.getName().equals("K4500-736362")) {
                        System.out.println("Found it");
                        selected_device = device;
                        // Need to set special permissions to be able to do this
                        mBluetoothAdapter.cancelDiscovery(); // slows down the connection if it's running
                        break;
                    } else {
                        System.out.println("Didn't find it");
                    }
                }
            }
        }

        return selected_device;
    }

    Vector<String> getBufferStrings(byte[] bytes, int byte_size, int column_number) {
        Vector<String> strings = new Vector<>();
        Vector<Integer> new_line_indices = new Vector<>();
        byte[] temp_bytes = new byte[byte_size];

        for (int i=0; i < byte_size; i++) {
            temp_bytes[i] = bytes[i];
        }

        //Find all of the "<CR><LF>" entries and remove.
        if (byte_size > 1)
        {
            for (int j=0; j<byte_size-1; ++j) {
                // 13 10
                if (temp_bytes[j] == 13 && temp_bytes[j + 1] == 10) {
                    new_line_indices.addElement(j);
                }
            }
        }

        //The string needs to be rebuilt with the "<CR><LF>" and "\r" removed
        String cat_str = new String();
        for (int i=0; i < new_line_indices.size()-1; i++) {
            cat_str += new String(temp_bytes, new_line_indices.get(i)+1, (new_line_indices.get(i+1)-1) - new_line_indices.get(i)+1).replace("\r", "");
        }

        //Find all of the "\n" entries and remove.
        new_line_indices.clear();
        for (int i=0; i < cat_str.length(); i++) {
            if (cat_str.charAt(i) == '\n') {
                new_line_indices.addElement(i);
            }
        }

        //Only add complete strings to the output strings...

        String[] temp_str_array =  cat_str
                .replace(">", "")
                .replace(" ", "")
                .split("\n");
        for (String aString : temp_str_array) {
            if (aString.split(",").length == column_number) {
                strings.addElement(aString);
            }
        }

        return strings;
    }

    public BluetoothDevice getSelected_device() {
        return selected_device;
    }

    public void setSelected_device(BluetoothDevice selected_device) {
        this.selected_device = selected_device;
    }

}
