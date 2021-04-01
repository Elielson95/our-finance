package br.ucsal.pdm.ourfinances;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Our Finances");
    }

    public void formActivity(View view) {
        Intent form = new Intent(this, FormActivity.class);
        startActivity(form);
    }
}
