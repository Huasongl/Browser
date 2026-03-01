package com.example.myapplication;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.nativelib.NativeLib;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "MainActivity";

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

               Intent intent = new Intent(this, MainActivity2.class);
               startActivity(intent);
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        if (View.VISIBLE == findViewById(R.id.button_1).getVisibility()) {

        }
        findViewById(R.id.button_2).setOnClickListener(this);
        findViewById(R.id.button_3).setOnClickListener(this);
        findViewById(R.id.button_4).setOnClickListener(this);
        findViewById(R.id.button_5).setOnClickListener(this);
        findViewById(R.id.button_6).setOnClickListener(this);
        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        DownloadManager
        SurfaceView surfaceView = new SurfaceView(this);
        surfaceView.getHolder();
        surfaceView.setVisibility(View.GONE);
        surfaceView.setVisibility(View.VISIBLE);

        WebChromeClient
        Fragment fragment = new Fragment();
        fragment.setRetainInstance(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_1) {
            Log.i(TAG, NativeLib.getInstance().stringFromJNI());
        } else if (v.getId() == R.id.button_6) {
            Log.i(TAG, "button_6 onClick. pid: " + android.os.Process.myPid());
            bindService(new Intent(this, MyService.class), new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    Log.i(TAG, "button_6 onServiceConnected. pid: " + android.os.Process.myPid());
                    IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                    try {
                        String string = iMyAidlInterface.getString();
                        Log.i(TAG, "button_6 getString. pid: 1. " + string);
                    } catch (RemoteException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName name) {

                }
            }, Context.BIND_AUTO_CREATE);
        }
    }
}