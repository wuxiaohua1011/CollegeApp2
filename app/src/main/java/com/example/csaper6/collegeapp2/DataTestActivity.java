package com.example.csaper6.collegeapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DataTestActivity extends AppCompatActivity {
private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_test);

        button = (Button)findViewById(R.id.button_test);
        textView = (TextView)findViewById(R.id.text_display);
    }
}
