package com.example.avs.randomvideocalling.Models;

import android.webkit.JavascriptInterface;

import com.example.avs.randomvideocalling.Activity.CallActivity;


public class InterfaceJava {

    CallActivity callActivity;

    public InterfaceJava(CallActivity callActivity) {
        this.callActivity = callActivity;
    }

    @JavascriptInterface
    public void onPeerConnected(){
        callActivity.onPeerConnected();
    }

}
