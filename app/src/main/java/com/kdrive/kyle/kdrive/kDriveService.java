package com.kdrive.kyle.kdrive;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kyle on 6/19/2016.
 */
public class kDriveService extends Service implements BluetoothAdapter.LeScanCallback{

    private static final String TAG = "kDriveService";

    //Device Name
    private static final String DEVICE_NAME = "kDrive";
    private int kDriveConnected;

    //UUID of kdrive BLE Service
    private static final UUID KDRIVE_SERVICE = UUID.fromString("6cc7ff65-51a9-4f5a-a741-2e7a5c7f400f");

    //UUID of BLE characteristics
    private static final UUID CLICKBUTTON1 = UUID.fromString("5d6853f1-51a5-4442-b9bf-19eb5aa080b9");//Button 1
    private static final UUID CLICKBUTTON2 = UUID.fromString("8f02502e-e775-4602-8237-eea1b8c028eb");//Button 2
    private static final UUID CLICKBUTTON3= UUID.fromString("9e746067-95a8-4d05-8dcc-4bf4c0cbb99d");//Button 3
    private static final UUID CLICKBUTTON4= UUID.fromString("5b58c282-d381-4a6e-969a-31d2f55a544d");//Button 4
    private static final UUID UPBUTTON = UUID.fromString("0b1a2f4c-bb49-4db0-b387-2449eca4f988");//Button 5
    private static final UUID DOWNBUTTON = UUID.fromString("56214007-6389-42c2-8945-32a120b4e569");//Button 6
    private static final UUID LEFTBUTTON = UUID.fromString("00e6423f-c59a-4a2b-97c8-e1cf6915fc75");//Button 7
    private static final UUID RIGHTBUTTON = UUID.fromString("402cf21b-94d5-4145-85a5-a3a3875ee937");//Button 8
    private static final UUID SLIDER1PACKET = UUID.fromString("f8c7bf32-3bca-4a52-aebb-b817900b623f");//Slider Pos
    private static final UUID SLIDERBYTECOUNT = UUID.fromString("dc246a90-11d0-4d38-8d75-cbac2b047d7f");//Slider Byte Count


    //UUID of CONFIG_DESCRIPTOR
    private static final UUID CONFIG_DESCRIPTOR = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    //public kDriveCallback kdcallback;

    private BluetoothManager BLEmanager;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothGatt mConnectedGatt;
    private BluetoothDevice kDriveDevice;

    private ProgressDialog mProgress;

    private ListView listViewScan ;
    private ArrayAdapter<String> listAdapter ;

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private IBinder myBinder = new MyBinder();

    public IncomingHandler mHandler;
    //final Messenger mMessenger = new Messenger(new IncomingHandler());
    public Messenger mMessenger;

    private int intCounter;

    ArrayList<Messenger> mClients = new ArrayList<Messenger>(); // Keeps track of all current registered clients.

    public android.support.v4.app.NotificationCompat.Builder notifBuilder;
    public NotificationManager notifManager;

    static final int NOTIFICATION_ID = 11783;

    public kDriveService() {

        //this.kdcallback = (kDriveCallback) context;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        Log.i(TAG, "service bound!");
        return mMessenger.getBinder();
        //return myBinder;
        //return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mHandler = new IncomingHandler();
        mMessenger = new Messenger(mHandler);

        Log.i(TAG, "kDriveService service created");

        BLEmanager = (BluetoothManager) getSystemService(BLUETOOTH_SERVICE);
        mBluetoothAdapter = BLEmanager.getAdapter();

         notifBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.wheel)
                        .setOngoing(true)
                        .setContentTitle("kDriveService service running")
                        .setColor(Color.RED)
                        .setContentText("Not Connected");


        Intent targetIntent = new Intent(this, kDriveService.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notifBuilder.setContentIntent(contentIntent);
        notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.notify(NOTIFICATION_ID, notifBuilder.build());

        //Connect to kDrive
        //this may need a separate thread
        //connect();

        intCounter =0;
        kDriveConnected=0;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "kDriveService service destroyed");

        //notifManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifManager.cancel(NOTIFICATION_ID);

        disconnectBLE();

        super.onDestroy();
    }

    public class MyBinder extends Binder {
        kDriveService getService(){
            return kDriveService.this;
        }
    }

    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            Bundle b = new Bundle();
            char key;
            int clickType;
            //super.handleMessage(msg);
            switch (msg.what){
                case kDrive.MSG_REGISTER_CLIENT:
                    mClients.add(msg.replyTo);
                    break;
                case kDrive.MSG_UNREGISTER_CLIENT:
                    mClients.remove(msg.replyTo);
                    break;
                case kDrive.MSG_KEYPRESSED:
                    b=msg.getData();
                    key = b.getChar("key");
                    clickType = b.getInt("clickType");
                    processKeyClick(key, clickType);
                    break;
                case kDrive.MSG_CONNECT:
                    connectBLE();
                    break;
                case kDrive.MSG_DISCONNECT:
                    disconnectBLE();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    //@Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        Log.i(TAG, "requesting BLE permission");

        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }

    public void connectBLE() {
        //State Machine goes:
        //1) Starts Scan
        //2) onLeScan callback runs - looks for "kDrive", connects if found
        //3) Connect if found
        //4) try to discover services
        //5) onServicesDiscovered callback runs
        //6) setNotifyNextSensor runs, descriptor is written
        //7) onDescriptorWrite callback runs
        //8) advance state macine, and run setNotifyNextSensor again

        Log.i(TAG, "Start Scan");
        //mDevices.clear();
        mBluetoothAdapter.startLeScan(this);
        //MHANDLER//setProgressBarIndeterminateVisibility(true);
        mHandler.postDelayed(mStopRunnable, 2500);
        sendMessage(null, kDrive.MSG_SCANSTARTED);
    }

    public void disconnectBLE(){
        if (mConnectedGatt != null) {
            //mConnectedGatt.disconnect();
            mConnectedGatt.close();
            mConnectedGatt = null;
            Log.i(TAG,"disconnected from GATT server");
        }

    }

    private Runnable mStopRunnable = new Runnable() {
        @Override
        public void run() {
            stopScan();
        }
    };
    private Runnable mStartRunnable = new Runnable() {
        @Override
        public void run() {
      //      startScan();
        }
    };

    private void stopScan() {
        mBluetoothAdapter.stopLeScan(this);
        Log.i(TAG,"stopScan()");
        //MHANDLER//setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

        //Log.i(TAG, "New LE Device: " + device.getName() + " @ " + rssi);
        /*
         * We are looking for kDrive devices only, so validate the name
         * that each device reports before connecting
         */
        if (DEVICE_NAME.equals(device.getName())) {
            Log.i(TAG,"Scanned and found a kDrive");

            /*
            * Make a connection with the device using the special LE-specific
            * connectGatt() method, passing in a callback for GATT events
            */

            if(mConnectedGatt==null) {
                kDriveDevice = device;
                Log.i(TAG,"Not connected to kDrive: connecting");
                mConnectedGatt = kDriveDevice.connectGatt(getApplicationContext(), false, mGattCallback);
                sendMessage(null, kDrive.MSG_KDRIVEFOUND);
                stopScan();
            }
            else{
                Log.i(TAG,"Already connected to kDrive");
            }
            //Display progress UI
            //MHANDLER//mHandler.sendMessage(Message.obtain(null, MSG_PROGRESS, "Connecting to " + device.getName() + "..."));

        }
        else {
            //Log.i(TAG, "Did not find any kDrives!");
        }
    }

    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        /* State Machine Tracking */
        private int mState = 0;
        private void reset() { mState = 0; }
        private void advance() { mState++; }

        /*
         * Enable notification of changes on the data characteristic for each sensor
         * by writing the ENABLE_NOTIFICATION_VALUE flag to that characteristic's
         * configuration descriptor.
         */
        private void setNotifyNextSensor(BluetoothGatt gatt) {
            BluetoothGattCharacteristic characteristic;
            BluetoothGattDescriptor desc;

            switch (mState) {
                case 0:
                    Log.d(TAG, "Set notify button 1");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(LEFTBUTTON);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 1:
                    Log.d(TAG, "Set notify button 2");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(RIGHTBUTTON);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 2:
                    Log.d(TAG, "Set notify button 3");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(UPBUTTON);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 3:
                    Log.d(TAG, "Set notify button 4");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(DOWNBUTTON);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 4:
                    Log.d(TAG, "Set notify button 5");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(CLICKBUTTON1);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 5:
                    Log.d(TAG, "Set notify button 6");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(CLICKBUTTON2);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 6:
                    Log.d(TAG, "Set notify button 7");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(CLICKBUTTON3);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 7:
                    Log.d(TAG, "Set notify button 8");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(CLICKBUTTON4);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
                    break;
                case 8:
                    Log.d(TAG, "Set notify sliderPos");
                    characteristic = gatt.getService(KDRIVE_SERVICE).getCharacteristic(SLIDER1PACKET);
                    desc = characteristic.getDescriptor(CONFIG_DESCRIPTOR);
                    desc.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    break;
                default:
                    //mHandler.sendEmptyMessage(MSG_DISMISS);
                    Log.i(TAG, "All Sensors Set to Notify");
                    //MHANDLER//mHandler.sendEmptyMessage(MSG_DISMISS);
                    return;
            }

            //Enable local notifications
            gatt.setCharacteristicNotification(characteristic, true);
            //Enabled remote notifications
            gatt.writeDescriptor(desc);
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            Log.i(TAG, "Connection State Change: "+status+" -> "+connectionState(newState));
            if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_CONNECTED) {
                Log.i(TAG,"STATE_CONNECTED");
                /*
                 * Once successfully connected, we must next discover all the services on the
                 * device before we can read and write their characteristics.
                 */
                kDriveConnected=1;
                gatt.discoverServices();
                //MHANDLER//mHandler.sendMessage(Message.obtain(null, MSG_PROGRESS, "Discovering Services..."));
                sendMessage(null, kDrive.MSG_BLECONNECTED);
                Log.i(TAG, "Discovering Services...");

                notifBuilder.setColor(Color.BLUE);
                notifBuilder.setContentText("Connected to kDrive!");
                notifManager.notify(NOTIFICATION_ID, notifBuilder.build());

            } else if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_DISCONNECTED) {
                /*
                 * If at any point we disconnect, send a message to clear the weather values
                 * out of the UI
                 */

                Log.i(TAG,"STATE_DISCONNECTED");
                //MHANDLER//mHandler.sendEmptyMessage(MSG_CLEAR);
                sendMessage(null,kDrive.MSG_BLEDISCONNECTED);

                notifBuilder.setColor(Color.RED);
                notifBuilder.setContentText("Not Connected");
                notifManager.notify(NOTIFICATION_ID, notifBuilder.build());

                //Does the connection close after the supervision timer?
                //Might need a watchdog broadcast on BLE device
                disconnectBLE();

            } else if (status != BluetoothGatt.GATT_SUCCESS) {
                /*
                 * If there is a failure at any stage, simply disconnect
                 */
                Log.i(TAG,"GATT NOT SUCCESSFUL");
                disconnectBLE();

            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.i(TAG, "Services Discovered: "+status);
            sendMessage(null, kDrive.MSG_SERVICESDISCOVERED);
            //MHANDLER//mHandler.sendMessage(Message.obtain(null, MSG_PROGRESS, "Found Services..."));
            /*
             * With services discovered, we are going to reset our state machine and start
             * working through the sensors we need to enable
             */
            reset();
            setNotifyNextSensor(gatt);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            //Not reading characteristics at this time.  Instead, getting the information from
            //the notifications
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            //After writing the enable flag, next we read the initial value
            int value;

            Log.i(TAG,"wrote characteristic= "+ status);
            value = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16,0);
            //mtextViewchar1.setText(String.format("%d", value));
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            /*
             * After notifications are enabled, all updates from the device on characteristic
             * value changes will be posted here.  Similar to read, we hand these up to the
             * UI thread to update the display.
             */
            //Log.i(TAG,"char changed");

            int i;
            byte[] gattCharacteristic = characteristic.getValue();
            int[] buttonPacket = new int[3];
            int[] sliderPacket = new int[20];

            //Log.i(TAG,"gatt char length:" + gattCharacteristic.length);

            //For each read, pass the data up to the UI thread to update the display

            if(
                    LEFTBUTTON.equals(characteristic.getUuid()) ||
                    RIGHTBUTTON.equals(characteristic.getUuid()) ||
                    UPBUTTON.equals(characteristic.getUuid()) ||
                    DOWNBUTTON.equals(characteristic.getUuid()) ||
                    CLICKBUTTON1.equals(characteristic.getUuid()) ||
                    CLICKBUTTON2.equals(characteristic.getUuid()) ||
                    CLICKBUTTON3.equals(characteristic.getUuid()) ||
                    CLICKBUTTON4.equals(characteristic.getUuid())
                    )
            {

                for (i=0;i<3;i++)
                {
                    buttonPacket[i] = unsignedByteToInt(gattCharacteristic[i]);

                    //Log.i(TAG,"index" + i + ": " + buttonPacket[i]);
                }

                processButtonClick(buttonPacket[2],buttonPacket[1]);
            }

            if(SLIDER1PACKET.equals(characteristic.getUuid()))
            {
                for (i=0;i<gattCharacteristic.length;i++)
                {
                    sliderPacket[i] = unsignedByteToInt(gattCharacteristic[i]);
                    //Log.i(TAG,"index: " + i + "    slider: " + sliderPacket[i]);
                    processSliderChange(1, sliderPacket);
                }

            }

                //gatt.readCharacteristic(characteristic);
                //Log.i(TAG,"trying to read button data");

        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            //Once notifications are enabled, we move to the next sensor and start over with enable
            //Log.i(TAG,"desc written: " +descriptor.getUuid());
            advance();
            setNotifyNextSensor(gatt);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            Log.d(TAG, "Remote RSSI: "+rssi);
        }

        private String connectionState(int status) {
            switch (status) {
                case BluetoothProfile.STATE_CONNECTED:
                    return "Connected";
                case BluetoothProfile.STATE_DISCONNECTED:
                    return "Disconnected";
                case BluetoothProfile.STATE_CONNECTING:
                    return "Connecting";
                case BluetoothProfile.STATE_DISCONNECTING:
                    return "Disconnecting";
                default:
                    return String.valueOf(status);
            }
        }
    };

    public void processKeyClick(char key, int clickType)
    {
        Log.i(TAG,String.format("%c :clicked in a SERVICE",key));

        int buttonID;

        switch(key){
            case 'a':
                buttonID = kDriveButton.BUTTON1;
                break;
            case 'd':
                buttonID = kDriveButton.BUTTON2;
                break;
            case 'w':
                buttonID = kDriveButton.BUTTON3;
                break;
            case 's':
                buttonID = kDriveButton.BUTTON4;
                break;
            case 'u':
                buttonID = kDriveButton.BUTTON5;
                break;
            case 'i':
                buttonID = kDriveButton.BUTTON6;
                break;
            case 'o':
                buttonID = kDriveButton.BUTTON7;
                break;
            case 'p':
                buttonID = kDriveButton.BUTTON8;
                break;
            default:
                buttonID = 0;
                Log.i(TAG,"BUTTON NOT RECOGNIZED");
        }

        Bundle b = new Bundle();
        b.putChar("key", key);
        b.putInt("clickType",clickType);

        sendMessage(b,kDrive.MSG_KEYPRESSED);
    }

    public void processSliderChange(int sliderID, int[] sliderPack){
        //Packet from BLE =
        //[startPosition][endPosition][speed][direction]
        Bundle b = new Bundle();

        b.putInt("slider", sliderID);
        b.putInt("sliderStart",sliderPack[0]);
        b.putInt("sliderEnd",sliderPack[1]);
        b.putInt("sliderSpeed",sliderPack[2]);
        b.putInt("sliderDirection",sliderPack[3]);

        sendMessage(b, kDrive.MSG_KDSLIDERCHANGED);

    }

    public void processButtonClick(int button, int clickType){

        Bundle b = new Bundle();

        switch(clickType)
        {
            case 1:
                b.putInt("clickType", kDriveButton.SINGLECLICK);
                break;
            case 2:
                b.putInt("clickType", kDriveButton.LONGCLICK);
                break;
            default:
                Log.i(TAG,"No clicktype found!");
                break;
        }

        b.putInt("buttonID", button);




        switch(button)
        {
            case 1:
                Log.i(TAG,"Button 1 Pushed");
                //simulateKey(KeyEvent.KEYCODE_A);
                break;
            case 2:
                Log.i(TAG,"Button 2 Pushed");
                //simulateKey(KeyEvent.KEYCODE_B);
                break;
            case 3:
                Log.i(TAG,"Button 3 Pushed");
                //simulateKey(KeyEvent.KEYCODE_C);
                break;
            case 4:
                Log.i(TAG,"Button 4 Pushed");
                //simulateKey(KeyEvent.KEYCODE_D);
                break;
            case 5:
                Log.i(TAG,"Up Button Pushed");
                //simulateKey(KeyEvent.KEYCODE_DPAD_UP);
                break;
            case 6:
                Log.i(TAG,"Down Button Pushed");
                //simulateKey(KeyEvent.KEYCODE_DPAD_DOWN);
                break;
            case 7:
                Log.i(TAG,"Left Button Pushed");
                //simulateKey(KeyEvent.KEYCODE_DPAD_LEFT);
                break;
            case 8:
                Log.i(TAG,"Right Button Pushed");
                //simulateKey(KeyEvent.KEYCODE_DPAD_RIGHT);
                break;
            default:
                Log.i(TAG,"Button not recognized");
                break;
        }

        sendMessage(b, kDrive.MSG_KDBUTTONPRESSED);

    }

    public void sendMessage(Bundle b, int msgWhat){
        for (int i=mClients.size()-1;i>=0;i--){
            try{
                Message msg = Message.obtain(null,msgWhat);//Sets the msg.what value
                msg.setData(b);
                mClients.get(i).send(msg);
            }
            catch (RemoteException e){
                //mClients.remove(i);
            }
        }
    }


    public int unsignedByteToInt(byte b) {
        return b & 0xFF;
    }

    //public static void simulateKey(final int KeyCode) {
    //public void simulateKey(final int KeyCode) {
//
        //new Thread() {
            //@Override
            //public void run() {
                //try {
                    //Instrumentation inst = new Instrumentation();
                    //inst.sendKeyDownUpSync(KeyCode);
                    //Log.i(TAG,String.format("%d",KeyCode));
                //} catch (Exception e) {
                    //Log.e("Exception sendKey", e.toString());
                //}
            //}
//
        //}.start();
    //}

    //interface kDriveCallback{
    //public void onKDriveButtonPushed(int buttonID, int clickType);
    //public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection);
    //public void onKeyboardPushed(char key, int clickType);
    //}





}
