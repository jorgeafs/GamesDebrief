package com.example.jorge.gamesdebrief;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.singleContainer)!=null){
            if(savedInstanceState != null){

            }else{

            }
        }else {
            if(savedInstanceState != null){

            }else{

            }
        }
    }
}
