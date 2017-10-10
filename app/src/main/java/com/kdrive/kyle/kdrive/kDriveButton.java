package com.kdrive.kyle.kdrive;

import android.util.Log;

/**
 * Created by Kyle on 6/30/2016.
 */
public class kDriveButton {

    private static final String TAG = "kDriveButton";

    public static final int NAVBUTTON = 1;
    public static final int GENERALBUTTON = 2;
    public static final int ROTARYWHEEL = 3;
    public static final int ROTARYWHEELCLICKER = 4;
    public static final int SCROLLWHEEL = 5;
    public static final int SCROLLWHEELCLICKER = 6;
    public static final int SLIDER = 7;


    public static final int BUTTON1 = 1;
    public static final int BUTTON2 = 2;
    public static final int BUTTON3 = 3;
    public static final int BUTTON4 = 4;
    public static final int BUTTON5 = 5;
    public static final int BUTTON6 = 6;
    public static final int BUTTON7 = 7;
    public static final int BUTTON8 = 8;
    public static final int SLIDER1 = 9;
    public static final int BUTTON10 = 10;
    public static final int BUTTON11 = 11;
    public static final int BUTTON12 = 12;
    public static final int BUTTON13 = 13;
    public static final int BUTTON14 = 14;
    public static final int BUTTON15 = 15;

    public static final int SINGLECLICK = 16;
    public static final int DOUBLECLICK = 17;
    public static final int LONGCLICK = 18;

    public int buttonID;


    //Button characteristics
    public boolean clickable;
    public boolean doubleClickable;
    public boolean longClickable;

    public boolean scrollable;

    public boolean slideable;


//    public int sliderVal;
    public boolean pressed;
    public int sliderVal;

//    public

    public kDriveButton(int buttonType, int buttonID) {

        this.setCharacteristics(buttonType);
        this.buttonID = buttonID;

    }

    private void setCharacteristics(int buttonType) {
        switch (buttonType) {
            case NAVBUTTON:
                //button is to be used to navigate (left/right/up/or down)
                //can be clicked, or held down to create multiple clicks
                clickable = true;
                doubleClickable = false;
                longClickable = false;
                scrollable = false;
                slideable = false;
                break;
            case GENERALBUTTON:
                //button is to be used to perform an action
                //can be clicked, double clicked, or long pressed
                clickable = true;
                doubleClickable = true;
                longClickable = true;
                scrollable = false;
                slideable = false;
                break;
            case ROTARYWHEEL:
                //Rotary encoder wheel
                //Can spin left or right.  Cannot be clicked
                clickable = false;
                doubleClickable = false;
                longClickable = false;
                scrollable = true;
                slideable = false;
                break;
            case ROTARYWHEELCLICKER:
                //Rotary encoder wheel
                //Can spin left or right.  Can be clicked, double clicked, or long pressed
                clickable = true;
                doubleClickable = true;
                longClickable = true;
                scrollable = true;
                slideable = false;
                break;
            case SCROLLWHEEL:
                //Mouse scroll wheel
                //Can forward and backwards.  Cannot be clicked
                clickable = false;
                doubleClickable = false;
                longClickable = false;
                scrollable = true;
                slideable = false;
                break;
            case SLIDER:
                //Slider, such as a volume control
                //Can forward and backwards.  Can be clicked, double clicked, or long pressed
                clickable = false;
                doubleClickable = false;
                longClickable = false;
                scrollable = false;
                slideable = true;
                break;
            default:
                Log.i(TAG, "Set to clickable by default.   Please select a button type");
                //button is to be used to scroll (left/right/up/or down)
                //can be clicked, or held down to create multiple clicks
                clickable = true;
                doubleClickable = true;
                longClickable = false;
                scrollable = false;
                slideable = false;
                break;
        }
    }

    public void printCharacteristics()
    {
        if(this.clickable==true){Log.i(TAG,"Button IS clickable");}else{Log.i(TAG,"Button IS NOT clickable ");}
        if(this.doubleClickable==true){Log.i(TAG,"Button IS double clickable");}else{Log.i(TAG,"Button IS NOT double clickable ");}
        if(this.longClickable==true){Log.i(TAG,"Button IS long clickable");}else{Log.i(TAG,"Button IS NOT long clickable");}
        if(this.scrollable==true){Log.i(TAG,"Button IS scrollable");}else{Log.i(TAG,"Button IS NOT scrollable");}
        if(this.slideable==true){Log.i(TAG,"Button IS slideable");}else{Log.i(TAG,"Button IS NOT slideable ");}
    }

}
