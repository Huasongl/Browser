package com.example.nativelib;

public class NativeLib {

    // Used to load the 'nativelib' library on application startup.
    static {
        System.loadLibrary("nativelib");
        System.out.println("zyh");
    }

    /**
     * A native method that is implemented by the 'nativelib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void dynamicJavaMethod01();
    public native int dynamicJavaMethod02(String string);
}