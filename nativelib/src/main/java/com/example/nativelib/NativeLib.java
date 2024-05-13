package com.example.nativelib;

public class NativeLib {

    private static final String TAG = "NativeLib";
    private static final NativeLib nativeLib = new NativeLib();

    // Used to load the 'nativelib' library on application startup.
    static {
        System.loadLibrary("nativelib");
    }

    public static NativeLib getInstance() {
        return nativeLib;
    }

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void dynamicJavaMethod01();

    public native int dynamicJavaMethod02(String string);

    public native int getNdkStudySoNumber();
}