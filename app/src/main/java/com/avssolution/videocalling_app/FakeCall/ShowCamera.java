package com.avssolution.videocalling_app.FakeCall;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {
    Camera camera;
    SurfaceHolder holder;
    public ShowCamera(Context context, Camera camera) {
        super(context);
        this.camera = camera;
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        Camera.Parameters params = camera.getParameters();
//        // chenge the orintiooooooooooo
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            params.set("orientation","portrait");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
        }else {
            params.set("orientation","landscape");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
        }

        camera.setParameters(params);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////////////////////////////////////

public void camSwich(){
        camera.stopPreview();

        if (camera !=null)
            camera.release();
        try {

          Camera.CameraInfo currentCamInfo = new Camera.CameraInfo();

           if (currentCamInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK){
                //switch camera to back camera
                Log.d("cam","back");
                camera=Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
            }
            else
//                if (currentCamInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK){
//                //switch camera to front camera
//                Log.d("cam","front");
//                camera=Camera.open(Camera.CameraInfo.CAMERA_FACING_FRONT);
//            }
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }catch (Exception e){
            Log.d("","");
        }




       }

}
