package com.ciscospark.androidsdk.internal;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cisco.spark.android.callcontrol.CallControlService;
import com.cisco.spark.android.locus.model.LocusData;
import com.cisco.spark.android.locus.model.LocusKey;
import com.cisco.spark.android.locus.model.MediaShare;
import com.ciscospark.androidsdk.phone.internal.PhoneImpl;
import com.ciscospark.androidsdk.phone.internal.RotationHandler;
import com.github.benoitdion.ln.Ln;

import javax.inject.Inject;

/**
 * Created by qimdeng on 3/1/18.
 */

public class AcquireScreenshotPermissionActivity extends Activity {
    protected static final int MEDIA_PROJECTION_REQUEST = 1;

    @Inject
    CallControlService _callControlService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) getSystemService(Context.MEDIA_PROJECTION_SERVICE);
            startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), MEDIA_PROJECTION_REQUEST);
        }else{
            finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MEDIA_PROJECTION_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                RotationHandler.setScreenshotPermission((Intent) data.clone());
            } else {
                Ln.i("User cancelled screen request");
            }
        }
        finish();
    }
}
