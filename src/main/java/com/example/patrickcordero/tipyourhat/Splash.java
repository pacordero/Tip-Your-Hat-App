package com.example.patrickcordero.tipyourhat;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Patrick Cordero on 8/23/2017.
 */

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
