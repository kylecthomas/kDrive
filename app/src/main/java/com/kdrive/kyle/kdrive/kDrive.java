package com.kdrive.kyle.kdrive;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;

/**
 * Created by Kyle on 6/19/2016.
 */
public class kDrive{

    private static final String TAG = "kDrive";

    public Context mContext;
    public Intent mIntent;

    static final kDriveButton upButton = new kDriveButton(kDriveButton.NAVBUTTON, kDriveButton.BUTTON5);
    static final kDriveButton downButton = new kDriveButton(kDriveButton.NAVBUTTON, kDriveButton.BUTTON6);
    static final kDriveButton leftButton = new kDriveButton(kDriveButton.NAVBUTTON, kDriveButton.BUTTON7);
    static final kDriveButton rightButton = new kDriveButton(kDriveButton.NAVBUTTON, kDriveButton.BUTTON8);
    static final kDriveButton click1Button = new kDriveButton(kDriveButton.GENERALBUTTON, kDriveButton.BUTTON1);
    static final kDriveButton click2Button = new kDriveButton(kDriveButton.GENERALBUTTON, kDriveButton.BUTTON2);
    static final kDriveButton click3Button = new kDriveButton(kDriveButton.GENERALBUTTON, kDriveButton.BUTTON3);
    static final kDriveButton click4Button = new kDriveButton(kDriveButton.GENERALBUTTON, kDriveButton.BUTTON4);
    static final kDriveButton slider1 = new kDriveButton(kDriveButton.SLIDER, kDriveButton.SLIDER1);

    static final int MSG_REGISTER_CLIENT = 1;
    static final int MSG_UNREGISTER_CLIENT = 2;
    static final int MSG_KEYPRESSED = 3;
    static final int MSG_KDBUTTONPRESSED = 4;
    static final int MSG_CONNECT = 5;
    static final int MSG_DISCONNECT = 6;
    static final int MSG_SCANSTARTED = 7;
    static final int MSG_KDRIVEFOUND = 8;
    static final int MSG_BLECONNECTED= 9;
    static final int MSG_SERVICESDISCOVERED = 10;
    static final int MSG_BLEDISCONNECTED = 11;
    static final int MSG_KDSLIDERCHANGED= 12;
    //static final int MSG_SET_STRING_VALUE = 4;


    public Messenger mService;
    final Messenger mMessenger = new Messenger(new IncomingHandler());
    public boolean isBound;


    public kDriveCallback kdcallback;

    public kDrive(Context context) {
        this.mContext = context;
        mService = null;
        isBound = false;
        this.kdcallback = (kDriveCallback) context;
    }

    public void callbackContext(Context context){
        this.kdcallback = (kDriveCallback) context;
    }

    public void startKdriveService() {
        mIntent = new Intent(mContext, kDriveService.class);
        mContext.startService(mIntent);
        mContext.bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void bindKdriveService() {
        mIntent = new Intent(mContext, kDriveService.class);
        mContext.bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    public void unBindKdriveService() {
        Log.i(TAG, "Attempting to stop kDrive Service");
        mContext.unbindService(serviceConnection);
    }

    public void stopKdriveService() {
        boolean stopped=false;

        Log.i(TAG, "Attempting to stop kDrive Service");
        mContext.unbindService(serviceConnection);
        stopped=mContext.stopService(mIntent);

        if(stopped){
            Log.i(TAG,"service stopped");
        }
        else{
            Log.i(TAG,"service was not stopped!");
        }
    }


    public void connectBLE(){
        sendMessage(null,MSG_CONNECT);
    }

    public void disconnectBLE(){
        sendMessage(null,MSG_DISCONNECT);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            isBound = true;

            sendMessage(null,MSG_REGISTER_CLIENT);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBound = false;
        }
    };

    public void sendMessage(Bundle b, int msgWhat){
        try{
            Message msg = Message.obtain(null,msgWhat);//Sets the msg.what value
            msg.setData(b);
            msg.replyTo = mMessenger;
            mService.send(msg);
        }
        catch (RemoteException e){
            //mClients.remove(i);
        }
    }

    class IncomingHandler extends Handler {
        char key;
        int buttonID;
        int clickType;
        int sliderID;
        int sliderStart;
        int sliderEnd;
        int sliderSpeed;
        int sliderDirection;

        Bundle b = new Bundle();

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REGISTER_CLIENT:
                    break;
                case MSG_UNREGISTER_CLIENT:
                    break;
                case MSG_KEYPRESSED:
                    b = msg.getData();
                    key = b.getChar("key");
                    clickType = b.getInt("clickType");
                    Log.i(TAG, String.format("%c : received from SERVICE",key));
                    kdcallback.onKeyboardPushed(key,clickType);
                    break;
                case MSG_KDBUTTONPRESSED:
                    b = msg.getData();
                    buttonID = b.getInt("buttonID");
                    clickType = b.getInt("clickType");
                    Log.i(TAG, String.format("kDrive button ID: %d pressed.  ClickType = %d", buttonID, clickType));
                    kdcallback.onKDriveButtonPushed(buttonID,clickType);
                    break;
                case MSG_KDSLIDERCHANGED:
                    b = msg.getData();
                    sliderID = b.getInt("sliderID");
                    sliderStart = b.getInt("sliderStart");
                    sliderEnd= b.getInt("sliderEnd");
                    sliderSpeed= b.getInt("sliderSpeed");
                    sliderDirection= b.getInt("sliderDirection");
                    Log.i(TAG, String.format("kDrive Slider:    start: %d   end: %d    speed: %d    direction: %d", sliderStart, sliderEnd, sliderSpeed, sliderDirection));
                    kdcallback.onKDriveSliderChanged(sliderID, sliderStart, sliderEnd, sliderSpeed, sliderDirection);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }


    public void processKeyClick(char key, int clickType) {

        Message msg = Message.obtain(null, MSG_KEYPRESSED);//set the msg.what value

        Bundle b = new Bundle();
        b.putChar("key", key);
        b.putInt("clickType", clickType);

        msg.setData(b);

        if (mService != null) {
            try {
                mService.send(msg);
            } catch (RemoteException e) {
            }
        } else {
            //Log.i(TAG, "IS THE SERVICE STARTED?");
        }
    }

    private boolean _longWButton = false;
    private boolean _longAButton = false;
    private boolean _longSButton = false;
    private boolean _longDButton = false;
    private boolean _longPButton = false;
    private boolean _longOButton = false;
    private boolean _longIButton = false;
    private boolean _longUButton = false;


    public void keyDown(int keyCode) {
        /*switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                if (!_longWButton) {
                }
            case KeyEvent.KEYCODE_A:
                if (!_longAButton) {
                }
            case KeyEvent.KEYCODE_S:
                if (!_longSButton) {
                }
            case KeyEvent.KEYCODE_D:
                if (!_longDButton) {
                }
        }*/

        //process key down clicks here
    }

    public void keyLongPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                _longWButton = true;
                break;
            case KeyEvent.KEYCODE_A:
                _longAButton = true;
                break;
            case KeyEvent.KEYCODE_S:
                _longSButton = true;
                break;
            case KeyEvent.KEYCODE_D:
                _longDButton = true;
                break;
            case KeyEvent.KEYCODE_P:
                _longPButton = true;
                break;
            case KeyEvent.KEYCODE_O:
                _longOButton = true;
                break;
            case KeyEvent.KEYCODE_I:
                _longIButton = true;
                break;
            case KeyEvent.KEYCODE_U:
                _longUButton = true;
                break;
        }
        Log.i("keyPress", ((char) (keyCode + 68)) + " long pressed!");
        processKeyClick((char) (keyCode + 68), kDriveButton.LONGCLICK);
    }


    public void keyUp(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_W:
                if (!_longWButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('w', kDriveButton.SINGLECLICK);
                }
                _longWButton = false;
                break;
            case KeyEvent.KEYCODE_A:
                if (!_longAButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('a', kDriveButton.SINGLECLICK);
                }
                _longAButton = false;
                break;
            case KeyEvent.KEYCODE_S:
                if (!_longSButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('s', kDriveButton.SINGLECLICK);
                }
                _longSButton = false;
                break;
            case KeyEvent.KEYCODE_D:
                if (!_longDButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('d', kDriveButton.SINGLECLICK);
                }
                _longDButton = false;
                break;
            case KeyEvent.KEYCODE_P:
                if (!_longPButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('p', kDriveButton.SINGLECLICK);
                }
                _longPButton = false;
                break;
            case KeyEvent.KEYCODE_O:
                if (!_longOButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('o', kDriveButton.SINGLECLICK);
                }
                _longOButton = false;
                break;
            case KeyEvent.KEYCODE_I:
                if (!_longIButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('i', kDriveButton.SINGLECLICK);
                }
                _longIButton = false;
                break;
            case KeyEvent.KEYCODE_U:
                if (!_longUButton) {
                    Log.i("keyPress", ((char) (keyCode + 68)) + " short pressed!");
                    processKeyClick('u', kDriveButton.SINGLECLICK);
                }
                _longUButton = false;
                break;
        }

    }

    //public static void simulateKey(final int KeyCode) {
    public static void simulateKey(final int KeyCode) {

        new Thread() {
            @Override
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendKeyDownUpSync(KeyCode);
                    Log.i(TAG,String.format("%d",KeyCode));
                } catch (Exception e) {
                    Log.e("Exception sendKey", e.toString());
                }
            }

        }.start();
    }





    interface kDriveCallback{
        public void onKDriveButtonPushed(int buttonID, int clickType);
        public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection);
        public void onKeyboardPushed(char key, int clickType);
    }

}



