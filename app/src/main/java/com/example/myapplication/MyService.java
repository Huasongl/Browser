package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyService";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate. pid: " + android.os.Process.myPid());
    }

    static class Mybind extends IMyAidlInterface.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void notifiy() throws RemoteException {
            Log.i(TAG, "notifiy. pid: " + android.os.Process.myPid());
        }

        @Override
        public String getString() throws RemoteException {
            notifiy();
            return "getString. pid: " + android.os.Process.myPid();
        }
    }
}
