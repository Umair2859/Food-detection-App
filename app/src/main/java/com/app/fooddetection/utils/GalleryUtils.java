package com.app.fooddetection.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.app.fooddetection.info.Info;

public class GalleryUtils implements Info {

    /**
     * STEP 1: ON BUTTON CLICK OPEN GALLERY
     * STEP 2: ON ACTIVITY RESULT CALL getBitmap
     */
    static int GALLERY_REQUEST_CODE = 662;

    private static boolean isStoragePermissionGranted(Context context) {
        if (context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            return false;
        }
    }

    public static void openGallery(Context context) {
        if (!isStoragePermissionGranted(context))
            return;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    public static void openGallery(Context context, int Code) {
        if (!isStoragePermissionGranted(context))
            return;

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity) context).startActivityForResult(intent, Code);
    }

    public static Bitmap getBitmap(Context context, int resultCode, Intent data) {
        if (data != null) {
            final Uri imageUri = data.getData();
            if (imageUri != null) {
                try {
                    return ImageProcessor.getImageFromResult(context, resultCode, data);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.i(TAG, "onActivityResult: Exception : " + e.getMessage());
                }
            }
        }
        return null;
    }
}
