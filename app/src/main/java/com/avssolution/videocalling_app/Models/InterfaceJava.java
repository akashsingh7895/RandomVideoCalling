package com.avssolution.videocalling_app.Models;

import android.webkit.JavascriptInterface;

import com.avssolution.videocalling_app.Activity.CallActivity;


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
