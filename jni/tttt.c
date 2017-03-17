#include <jni.h>

jclass  gJniClass;
jmethodID gJinMethod;

//---------------------------------------------------------------
JNIEXPORT jstring JNICALL
Java_com_example_tttt_MainActivity_myJni(JNIEnv* env, jclass cls,jstring param)
{
     char   tChar[256];
     const char  *tpParam;

     gJniClass = cls;
     gJinMethod = 0;

     gJinMethod=(*env)->GetStaticMethodID(env,gJniClass,"myCallbackFunc","(Ljava/lang/String;)V");
     if(gJinMethod == 0)
         return (*env)->NewStringUTF(env, "-2");

     strcpy(tChar,"Hello Eagle 2");

     (*env)->CallStaticVoidMethod(env,gJniClass,gJinMethod,(*env)->NewStringUTF(env, tChar));

     DisplayCallBack(env,tChar);

     tpParam =(*env)->GetStringUTFChars(env,param,0);

     return param;
}



//---------------------------------------------------------------

void DisplayCallBack(JNIEnv* env,char nMsg[])
{
     char tChars[256];

     strcpy(tChars,nMsg);
    // (*env)->CallStaticVoidMethod(env,gJniClass,gJinMethod,(*env)->NewStringUTF(env, tChars));
}
