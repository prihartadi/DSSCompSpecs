package com.example.prihartadi.dsscompspecs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class A_HelpDetail extends AppCompatActivity {

    TextView title, detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a__help_detail);

        title = (TextView)findViewById(R.id.textTitle);
        detail = (TextView)findViewById(R.id.textDetail);
        title.setText(getIntent().getStringExtra("title"));
        detail.setText(getIntent().getStringExtra("detail"));
    }
}
