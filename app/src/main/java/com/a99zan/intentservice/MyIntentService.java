package com.a99zan.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by 99zan on 2018/4/3.
 */

public class MyIntentService extends IntentService {

    private final static String TAG = "MyIntentService";

    public static final String ACTION_DOWN_IMG = "down.image";
    public static final String ACTION_DOWN_VID = "down.vid";
    public static final String ACTION_DOWN_PROGRESS = "com.zpengyong.down.progress";
    public static final String ACTION_SERVICE_STATE = "com.zpengyong.service.state";
    public static final String PROGRESS = "progress";
    public static final String SERVICE_STATE = "service_state";

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        sendServiceState("onCreate");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent thread:" + Thread.currentThread());
        String action = intent.getAction();
        if (action.equals(ACTION_DOWN_IMG)) {
            for (int i = 0; i < 100; i++) {
                try { //模拟耗时操作
                    Thread.sleep(50);
                } catch (Exception e) {
                }
                sendProgress(i);
            }
        } else if (action.equals(ACTION_DOWN_VID)) {
            for (int i = 0; i < 100; i++) {
                try { //模拟耗时操作
                    Thread.sleep(70);
                } catch (Exception e) {
                }
                sendProgress(i);
            }
        }
        Log.i(TAG, "onHandleIntent end");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        sendServiceState("onDestroy");
    }

    //发送Service的状态
    private void sendServiceState(String state) {
        Intent intent = new Intent();
        intent.setAction(ACTION_SERVICE_STATE);
        intent.putExtra(SERVICE_STATE, state);
        sendBroadcast(intent);
    }

    //发送进度
    private void sendProgress(int progress) {
        Intent intent = new Intent();
        intent.setAction(ACTION_DOWN_PROGRESS);
        intent.putExtra(PROGRESS, progress);
        sendBroadcast(intent);
    }

}
