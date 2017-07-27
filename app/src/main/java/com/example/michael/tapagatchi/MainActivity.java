package com.example.michael.tapagatchi;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {

    private ImageButton tapagachiAvatar;
    private ProgressBar happinessProgressBar;
    private int totalNumberOfHappinessStates = 120;
    private int happinessLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapagachiAvatar = (ImageButton) findViewById(R.id.tapagachiAvatar);
        happinessProgressBar = (ProgressBar) findViewById(R.id.happinessProgressBar);
        tapagachiAvatar.setOnClickListener(onClick());


        //every 5 second or so .. decrease happiness by 1 and update the ui
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(happinessLevel > 1){
                    happinessLevel--;
                    mHandler.obtainMessage(1).sendToTarget();
                }
            }
        }, 0, 5000);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(happinessLevel < totalNumberOfHappinessStates){
                        happinessLevel += 5;
                    }
                    updateHappinessUi();
                }
                catch(Exception e){

                }
            }
        };
    }

    /**
     * handler thanks to https://stackoverflow.com/questions/6700802/android-timer-updating-a-textview-ui
     */
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            updateHappinessUi();
        }
    };

    private void updateHappinessUi(){
        Context context = tapagachiAvatar.getContext();
        int imageId = context.getResources().getIdentifier("tapagatchi_" + happinessLevel, "drawable", context.getPackageName());
        tapagachiAvatar.setImageResource(imageId);

        happinessProgressBar.setProgress(happinessLevel * 100/totalNumberOfHappinessStates);
    }
}
