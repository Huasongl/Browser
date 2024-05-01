#include <jni.h>
#include <string>
#include <android/log.h>
#include <pthread.h>

#define TAG "JNISTUDY"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__);


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

void DynamicJavaMethod01(JNIEnv *env, jobject j_object) {
    LOGI("C++ DynamicJavaMethod01")
}

void *DynamicJavaMethod02(JNIEnv *env, jobject j_object, std::string string) {
    LOGI("C++ DynamicJavaMethod02")
    return 0;
}

static const JNINativeMethod gMethods[] = {
        {"dynamicJavaMethod01", "()V",                   (void *) (DynamicJavaMethod01)},
        {"dynamicJavaMethod02", "(Ljava/lang/String;)I", (void *) (DynamicJavaMethod02)}
};

JavaVM *vm;

jint JNI_OnLoad(JavaVM *vm, void *args) {
    ::vm = vm;

    JNIEnv *env;
    jint r = vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6);
    if (r) {
        return -1;
    }

    jclass main_activity_class = env->FindClass("com/example/nativelib/NativeLib");
    env->RegisterNatives(main_activity_class, gMethods, sizeof gMethods / sizeof(JNINativeMethod));
    LOGI("C++ JNI_OnLoad")
    return JNI_VERSION_1_6;
}