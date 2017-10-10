package com.kdrive.kyle.kdrive;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.KeyEvent;

import com.github.tamir7.contacts.Contact;
import com.github.tamir7.contacts.Contacts;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class contactActivity extends Activity implements kDrive.kDriveCallback{
    private ListView mListView;
    ArrayList<String> contactList;
    Cursor cursor;
    int counter;

    private static final String TAG = "mapsActivity";

    public kDrive kdcba = new kDrive(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list_view);

        mListView = (ListView) findViewById(R.id.list);
        Contacts.initialize(this);

        //Read contacts on separate thread
        //new Thread(new Runnable() {
        //@Override
        //public void run() {
        //getContacts();
        //}
        //}).start();

        //mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //@Override
        //public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        ////TODO do whavetever you want with the list data
        //Toast.makeText(getApplicationContext(), "item clicked :\n" + contactList.get(position), Toast.LENGTH_SHORT).show();
        //}
        //});

        kdcba.bindKdriveService();


        Log.i("CONTACTACTIVITY","-LOADED-");
        loadContacts();


    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        kdcba.unBindKdriveService();
    }

    public void loadContacts(){
        //TODO: find all contacts
        List<Contact> contacts = Contacts.getQuery().hasPhoneNumber().find();

        ArrayList<String> contactNames = new ArrayList<String>();


        //ArrayAdapter<List> contactsAdapter = new ArrayAdapter<List>(this, R.layout.contacts_list_item, contacts);

        //Log.e(TAG, new GsonBuilder().setPrettyPrinting().create().toJson(contacts));
        //for (int i = 0 ; i < contacts.size() ; i++)
        //{
            //Log.i("contact" , String.format("%d of %d: %s",i+1, contacts.size(), contacts.get(i).getDisplayName()));
            //contactNames.add(contacts.get(i).getDisplayName());
//
        //}


        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.contacts_list_item,contactNames);
        ArrayAdapter<Contact> adapter = new ContactAdapter(this, contacts);

        ListView lv = (ListView) findViewById(R.id.listviewContacts);
        lv.setAdapter(adapter);

    }


    @Override
    public void onKDriveButtonPushed(int buttonID, int clickType) {
        Log.i(TAG,"mmmmhhmmmm");

        switch (buttonID) {
            case kDriveButton.BUTTON1:
                break;
            case kDriveButton.BUTTON2:
                break;
            case kDriveButton.BUTTON3:
                break;
            case kDriveButton.BUTTON4:
                break;
            case kDriveButton.BUTTON5:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_UP);
                break;
            case kDriveButton.BUTTON6:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_DOWN);
                break;
            case kDriveButton.BUTTON7:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_LEFT);
                break;
            case kDriveButton.BUTTON8:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_RIGHT);
                break;
            default:
        }
    }

    @Override
    public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection) {
        Log.i(TAG,"whimmywhamwhamwozzle");
    }

    @Override
    public void onKeyboardPushed(char key, int clickType) {
        Log.i(TAG,"booya");
    }

}
