package com.example.attendance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    EditText et,et1;
    String st,st1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn=findViewById(R.id.button4);
        et=findViewById(R.id. editText1);
        et1=findViewById(R.id.editText2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Main3Activity.this,Main2Activity.class);
                st=et.getText().toString();
                st1=et1.getText().toString();
                i.putExtra("Value",st);
                i.putExtra("Value1",st1);
                startActivity(i);
                finish();
            }
        });
    }
}
