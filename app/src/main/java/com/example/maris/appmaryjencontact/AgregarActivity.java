package com.example.maris.appmaryjencontact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgregarActivity extends AppCompatActivity {

    private CircleImageView imgcontactN;
    private EditText nombreA;
    private EditText emailA;
    private EditText numeroA;
    private Button agregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
    }
}
