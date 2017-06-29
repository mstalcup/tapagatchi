package com.example.michael.tapagatchi;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends ActionBarActivity {

    private ImageButton tapagachiAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tapagachiAvatar = (ImageButton) findViewById(R.id.tapagachiAvatar);
        tapagachiAvatar.setOnClickListener(onClick());
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
            int happinessLevel = 0;
            @Override
            public void onClick(View v) {
                try {
                    if(happinessLevel < 4){
                        happinessLevel++;
                    }
                    Context context = tapagachiAvatar.getContext();
                    int imageId = context.getResources().getIdentifier("tapagatchi_" + happinessLevel, "drawable", context.getPackageName());
                    tapagachiAvatar.setImageResource(imageId);
                }
                catch(Exception e){

                }
            }
        };
    }
}
