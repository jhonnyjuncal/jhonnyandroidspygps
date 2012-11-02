package com.example.prueba;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PosicionesActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posiciones);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_posiciones, menu);
        return true;
    }
}
