package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.nativelib.NativeLib;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "JNISTUDY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
//
//        Intent intent = new Intent();
//        intent.setClass(this, MainActivity2.class);
//        startActivity(intent);
//        findViewById(R.id.button_1).getContext().startActivity();
        //android jni include c++ link
        //JNI 动态注册
        //
        NativeLib nativeLib = new NativeLib();
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i(TAG, "dynamicJavaMethod01");
//                nativeLib.dynamicJavaMethod01();
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setComponent(new ComponentName("com.flyme.auto.browser", "com.android.browser.BrowserActivity"));
                intent.setData(Uri.parse("mzbrowser://com.flyme.auto.browser/?url=https://www.baidu.com"));
                startActivity(intent);
            }
        });

        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "dynamicJavaMethod02");
                nativeLib.dynamicJavaMethod02("xx");
            }
        });
    }
}