package com.kdrive.kyle.kdrive;

import android.graphics.Camera;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, kDrive.kDriveCallback {

    private GoogleMap mMap;

    public kDrive kdcba = new kDrive(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        kdcba.bindKdriveService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        kdcba.unBindKdriveService();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng attpark= new LatLng(37.7785, -122.389);
        mMap.addMarker(new MarkerOptions().position(attpark).title("Welcome to ATT Park"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(attpark));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(13));
    }


    @Override
    public void onKDriveButtonPushed(int buttonID, int clickType) {
        float scrollX = 100f;
        float scrollY = 100f;


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
                mMap.moveCamera(CameraUpdateFactory.scrollBy(0,-scrollY));
                break;
            case kDriveButton.BUTTON6:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_DOWN);
                mMap.moveCamera(CameraUpdateFactory.scrollBy(0,scrollY));
                break;
            case kDriveButton.BUTTON7:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_LEFT);
                mMap.moveCamera(CameraUpdateFactory.scrollBy(-scrollX,0));
                break;
            case kDriveButton.BUTTON8:
                kDriveCallbackAdapter.simulateKey(KeyEvent.KEYCODE_DPAD_RIGHT);
                mMap.moveCamera(CameraUpdateFactory.scrollBy(scrollX,0));
                break;
            default:
        }

    }

    @Override
    public void onKDriveSliderChanged(int sliderID, int sliderStart, int sliderEnd, int sliderSpeed, int sliderDirection) {

    }

    @Override
    public void onKeyboardPushed(char key, int clickType) {

    }
}
