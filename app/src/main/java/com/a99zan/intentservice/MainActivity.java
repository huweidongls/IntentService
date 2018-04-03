package com.a99zan.intentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InnerReceiver innerReceiver;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private TextView tv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
        
    }

    private void init() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        tv = (TextView) findViewById(R.id.tv);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.pro);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.zpengyong.service.state");
        intentFilter.addAction("com.zpengyong.down.progress");
        innerReceiver = new InnerReceiver();
        registerReceiver(innerReceiver, intentFilter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn1:
                intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.setAction(MyIntentService.ACTION_DOWN_IMG);
                startService(intent);
                break;
            case R.id.btn2:
                intent = new Intent(MainActivity.this, MyIntentService.class);
                intent.setAction(MyIntentService.ACTION_DOWN_VID);
                startService(intent);
                break;
            case R.id.btn3:

                break;
        }
    }

    class InnerReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case "com.zpengyong.service.state":
                    String state = intent.getStringExtra("service_state");
                    tv.setText(state);
                    break;
                case "com.zpengyong.down.progress":
                    int progress = intent.getIntExtra("progress", 0);
                    progressBar.setProgress(progress);
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(innerReceiver);
    }
}
