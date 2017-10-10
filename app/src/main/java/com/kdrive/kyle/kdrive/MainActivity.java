package com.kdrive.kyle.kdrive;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements kDrive.kDriveCallback{

    private static final String TAG = "MainActivity";

    //private SparseArray<BluetoothDevice> mDevices;
    private TextView mtextViewchar1, mtextViewchar2, keyPressedText, clickTypeText;
    private TextView sliderStartText, sliderEndText, sliderSpeedText, sliderDirectionText;
    private SeekBar slider1;
    private Button tempButton;

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    final kDrive myKDrive = new kDrive(this);


   /*
    final kDriveCallbackAdapter kdrivecallback = new kDriveCallbackAdapter() {
        @Override
        public void onKDriveButtonPushed(int buttonID, int clickType) {
            //super.onKDriveButtonPushed(buttonID, clickType);
            KDriveButtonPushed(buttonID, clickType);
        }

        @Override
        public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection) {
            //super.onKDriveSliderChanged(sliderID, sliderStart, sliderEnd, sliderSpeed, sliderDirection);
            KDriveSliderChanged(sliderID, sliderStart, sliderEnd, sliderSpeed, sliderDirection);
        }

        @Override
        public void onKeyboardPushed(char key, int clickType) {
            //super.onKeyboardPushed(key, clickType);
            KeyboardPushed(key, clickType);
        }
    };
*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtextViewchar1 = (TextView) findViewById(R.id.text_kdrive1);
        mtextViewchar2 = (TextView) findViewById(R.id.text_kdrive2);
        keyPressedText = (TextView) findViewById(R.id.keyPressed);
        sliderStartText= (TextView) findViewById(R.id.sliderStart);
        sliderEndText= (TextView) findViewById(R.id.sliderEnd);
        sliderSpeedText= (TextView) findViewById(R.id.sliderSpeed);
        sliderDirectionText= (TextView) findViewById(R.id.sliderDirection);
        clickTypeText = (TextView) findViewById(R.id.clickType);
        slider1 = (SeekBar) findViewById(R.id.slider1);


        Button startButton = (Button) findViewById(R.id.buttonLED1);
        Button stopButton = (Button) findViewById(R.id.buttonLED2);
        Button connectButton = (Button) findViewById(R.id.bleConnect);
        Button disconnectButton = (Button) findViewById(R.id.bleDisconnect);
        Button launchList = (Button) findViewById(R.id.launchList);
        Button launchMap= (Button) findViewById(R.id.launchMap);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKDrive.startKdriveService();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKDrive.stopKdriveService();
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKDrive.connectBLE();
            }
        });

        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKDrive.disconnectBLE();
            }
        });

        launchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), contactActivity.class);
                startActivity(intent);
            }
        });
        launchMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
            }
        });

        checkBLE();
        checkReadContacts();
    }

    public void checkReadContacts(){

        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        }


        //CHECK THAT BLUETOOTH IS ON
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Android M Permission check 
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can connect to a BLE device.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    //@Override 
                    @TargetApi(Build.VERSION_CODES.M)
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                builder.show();
            }
        }
    }



    public void checkBLE(){
        //CHECK THAT BLUETOOTH IS ON
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // Android M Permission check 
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can connect to a BLE device.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    //@Override 
                    @TargetApi(Build.VERSION_CODES.M)
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                    }
                });
                builder.show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                //showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        /*
         * We need to enforce that Bluetooth is first enabled, and take the
         * user to settings to enable it if they have not done so.
         */


        /*
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            //Bluetooth is disabled
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBtIntent);
            finish();
            return;
        }
        */

        /*
         * Check for Bluetooth LE Support.  In production, our manifest entry will keep this
         * from installing on these devices, but this will allow test devices or other
         * sideloads to report whether or not the feature exists.
         */
        /*
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "No LE Support.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        */

        /*

        if (mConnectedGatt == null) {
            //startScan();
        }
        */

        clearDisplayValues();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //myKDrive.unBindKdriveService();
    }

    private void clearDisplayValues() {
        mtextViewchar1.setText("---");
        mtextViewchar2.setText("---");
    }

    /* Methods to extract sensor data and update the UI */
    private void updatekDriveValues(BluetoothGattCharacteristic characteristic) {
        //double com.kdrive.kyle.kdrive.kDriveService = SensorTagData.extractHumidity(characteristic);

        Log.i(TAG, "Reading GATT characteristic");

        Integer kDriveInt = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0);

        mtextViewchar1.setText(String.format("%d", kDriveInt));
    }


    //Array of keys to track
    int[] validKeys = {
            KeyEvent.KEYCODE_A,
            KeyEvent.KEYCODE_S,
            KeyEvent.KEYCODE_D,
            KeyEvent.KEYCODE_W,
            KeyEvent.KEYCODE_P,
            KeyEvent.KEYCODE_O,
            KeyEvent.KEYCODE_I,
            KeyEvent.KEYCODE_U
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        for(int i:validKeys) {
            if (keyCode == i) {
                myKDrive.keyDown(keyCode);
                event.startTracking();
            }
        }
        return true;
    }
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        for(int i:validKeys) {
            if (keyCode == i) {
                myKDrive.keyLongPress(keyCode);
                return true;
            }
        }
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        for(int i:validKeys){
            if (keyCode == i){
                myKDrive.keyUp(keyCode);
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }


    //DO SOMETHING WITH KDRIVE BUTTON CLICK HERE
    @Override
    public void onKDriveButtonPushed(int buttonID, int clickType) {

        Intent intent;

        Log.i(TAG, "kDrive Button Pressed");

        keyPressedText.setText(""+buttonID);

        switch (clickType){
            case kDriveButton.SINGLECLICK:
                clickTypeText.setText("Single Click");
                break;
            case kDriveButton.LONGCLICK:
                clickTypeText.setText("Long Click");
                break;
            default:
                clickTypeText.setText("not recognized");
                break;

        }

        switch (buttonID)
        {
            case kDriveButton.BUTTON1:
                tempButton = (Button) findViewById(R.id.button1);
                intent = new Intent(getApplicationContext(), contactActivity.class);
                startActivity(intent);
                break;
            case kDriveButton.BUTTON2:
                tempButton = (Button) findViewById(R.id.button2);
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case kDriveButton.BUTTON3:
                tempButton = (Button) findViewById(R.id.button3);
                break;
            case kDriveButton.BUTTON4:
                tempButton = (Button) findViewById(R.id.button4);
                break;
            case kDriveButton.BUTTON5:
                tempButton = (Button) findViewById(R.id.buttonUp);
                break;
            case kDriveButton.BUTTON6:
                tempButton = (Button) findViewById(R.id.buttonDown);
                break;
            case kDriveButton.BUTTON7:
                tempButton = (Button) findViewById(R.id.buttonLeft);
                break;
            case kDriveButton.BUTTON8:
                tempButton = (Button) findViewById(R.id.buttonRight);
                break;
            default:
                Log.i(TAG, "Button not recognized");
                tempButton = (Button) findViewById(R.id.buttonLeft);
                break;

        }

        //initiate the button
        tempButton.performClick();
        tempButton.setPressed(true);
        tempButton.invalidate();
        // delay completion till animation completes
        tempButton.postDelayed(new Runnable() {  //delay button
            public void run() {
                tempButton.setPressed(false);
                tempButton.invalidate();
                //any other associated action
            }
        }, 100);  // .1secs delay time

    }

    //DO SOMETHING WITH SLIDER VALUE CHANGE
    @Override
    public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection){

        sliderStartText.setText(""+sliderStart);
        sliderEndText.setText(""+sliderEnd);
        sliderSpeedText.setText(""+sliderSpeed);
        sliderDirectionText.setText(""+sliderDirection);

        slider1.setProgress(sliderEnd);


    }

    //DO SOMETHING WITH KEYBOARD CLICK HERE
    @Override
    public void onKeyboardPushed(char key, int clickType){
        Log.i(TAG, "Key Pressed");

        keyPressedText.setText(""+key);

        switch (clickType){
            case kDriveButton.SINGLECLICK:
                clickTypeText.setText("Single Click");
                break;
            case kDriveButton.LONGCLICK:
                clickTypeText.setText("Long Click");
                break;
            default:
                clickTypeText.setText("not recognized");
                break;

        }
    }




}

