package com.example.maris.appmaryjencontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactoActivity extends AppCompatActivity {

    private CircleImageView imgcontact;
    private TextView nombrec;
    private TextView emailc;
    private TextView numeroc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        imgcontact=findViewById(R.id.foto);
        nombrec=findViewById(R.id.nombrecontactoA);
        emailc=findViewById(R.id.emailcA);
        numeroc=findViewById(R.id.numeroA);

        //Recibiendo datos
        Intent intent =  getIntent();
        int img = intent.getExtras().getInt("Imagen");
        String nombre = intent.getExtras().getString("Nombre");
        String email = intent.getExtras().getString("Email");
        String numero = intent.getExtras().getString("Numero");

        //Seteando valores
        imgcontact.setImageResource(img);
        numeroc.setText(nombre);
        emailc.setText(email);
        numeroc.setText(numero);

    }
}
