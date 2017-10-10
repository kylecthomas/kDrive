package com.kdrive.kyle.kdrive;

import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.KeyEvent;

import static com.kdrive.kyle.kdrive.kDrive.MSG_REGISTER_CLIENT;

/**
 * Created by Kyle on 9/6/2017.
 */

//public abstract class kDriveCallbackAdapter implements kDrive.kDriveCallback {
public class kDriveCallbackAdapter {

    private static final String TAG = "kDriveCallbackAdapter";

    public boolean isBound;
    public Messenger mService;
    final Messenger mMessenger = new Messenger(new IncomingHandler());

    public Context mContext;
    public Intent mIntent;


    public kDriveCallback kdcallback;

    public kDriveCallbackAdapter(Context context) {
        this.mContext = context;
        mService = null;
        isBound = false;
        this.kdcallback = (kDriveCallback) context;
    }

    public void bindKdriveService() {
        //startService(new Intent(getBaseContext(), kDriveService.class));
        //startService(new Intent(getApplicationContext(), kDriveService.class));
        //Log.i(TAG,"startKdriveServic");
        mIntent = new Intent(mContext, kDriveService.class);
        mContext.bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

        public void unBindKdriveService() {
        Log.i(TAG, "Attempting to stop kDrive Service");
        mContext.unbindService(serviceConnection);
    }

    public void sendMessage(Bundle b, int msgWhat) {
        try {
            Message msg = Message.obtain(null, msgWhat);//Sets the msg.what value
            msg.setData(b);
            msg.replyTo = mMessenger;
            mService.send(msg);
        } catch (RemoteException e) {
            //mClients.remove(i);
        }
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            isBound = true;

            sendMessage(null, MSG_REGISTER_CLIENT);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            isBound = false;
        }
    };


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
                case kDrive.MSG_UNREGISTER_CLIENT:
                    break;
                case kDrive.MSG_KEYPRESSED:
                    b = msg.getData();
                    key = b.getChar("key");
                    clickType = b.getInt("clickType");
                    Log.i(TAG, String.format("%c : received from SERVICE", key));
                    kdcallback.onKeyboardPushed(key, clickType);
                    break;
                case kDrive.MSG_KDBUTTONPRESSED:
                    b = msg.getData();
                    buttonID = b.getInt("buttonID");
                    clickType = b.getInt("clickType");
                    Log.i(TAG, String.format("kDrive button ID: %d pressed.  ClickType = %d", buttonID, clickType));
                    kdcallback.onKDriveButtonPushed(buttonID, clickType);
                    break;
                case kDrive.MSG_KDSLIDERCHANGED:
                    b = msg.getData();
                    sliderID = b.getInt("sliderID");
                    sliderStart = b.getInt("sliderStart");
                    sliderEnd = b.getInt("sliderEnd");
                    sliderSpeed = b.getInt("sliderSpeed");
                    sliderDirection = b.getInt("sliderDirection");
                    Log.i(TAG, String.format("kDrive Slider:    start: %d   end: %d    speed: %d    direction: %d", sliderStart, sliderEnd, sliderSpeed, sliderDirection));
                    kdcallback.onKDriveSliderChanged(sliderID, sliderStart, sliderEnd, sliderSpeed, sliderDirection);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

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


    interface kDriveCallback {
        public void onKDriveButtonPushed(int buttonID, int clickType);

        public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection);

        public void onKeyboardPushed(char key, int clickType);
    }

}
