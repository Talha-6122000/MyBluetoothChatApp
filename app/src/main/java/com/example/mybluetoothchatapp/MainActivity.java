package com.example.mybluetoothchatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.bluetooth.BluetoothAdapter.getDefaultAdapter;

public class MainActivity extends AppCompatActivity {
    //Well here we made two button for turning Bluetooth On and Off
    //We first declare two objects/varaible of Button.
    Button buttonOn,buttonoff;
//     Here we declare an object of BluetoothAdapter
//     Let's see what is an Bluetooth Adapter ?
//     The bluetooth adapter let's you perfome Fundamental Bluetooth Tasks
//     such as initiate device discovery, query a list of bonded (paired) devices,
//     instantiate a BluetoothDevice using a known MAC address, 
//     and create a BluetoothServerSocket to listen for connection requests from other devices
//     Adapter is a bridge between UI component and data source that helps us to fill data in UI component. 
//     It holds the data and send the data to an Adapter view then view can takes the data from the adapter view 
//     and shows the data on different views like as ListView, GridView, Spinner etc
    BluetoothAdapter mybluetoothAdapter;
    Intent btEnablingIntent;
    int requestCodeForEnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonOn=findViewById(R.id.bluetooth_on);
        buttonoff=findViewById(R.id.bluetooth_off);
        mybluetoothAdapter= getDefaultAdapter();
        requestCodeForEnable=1;
        bluetoothOnMethod();
        bluetoothOffMethod();
    }
// if user click on on the off button bluetooth will be turned off 
    private void bluetoothOffMethod() {
        buttonoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mybluetoothAdapter.isEnabled())
                {
                    mybluetoothAdapter.disable();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==requestCodeForEnable)
        {
            if (resultCode==RESULT_OK)
            {
                Toast.makeText(this, "Bluetooth is Enabled", Toast.LENGTH_LONG).show();
            }
            else if(resultCode==RESULT_CANCELED) {
                Toast.makeText(this, "Bluetooth Enabling cancelled", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private void bluetoothOnMethod() {
        buttonOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mybluetoothAdapter==null)
                {
                    Toast.makeText(MainActivity.this, "Bluetooth is not Supported in " +
                            "this device", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (!mybluetoothAdapter.isEnabled())
                    {
                      btEnablingIntent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                      startActivityForResult(btEnablingIntent,requestCodeForEnable);
                    }

                }
            }
        });
    }

}
