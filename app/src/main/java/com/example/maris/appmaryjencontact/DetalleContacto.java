package com.example.maris.appmaryjencontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleContacto extends AppCompatActivity {

    private ImageView img_contacto;
    private TextView nombre;
    private TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detallecontacto);

        img_contacto=findViewById(R.id.foto);
        nombre=findViewById(R.id.nombrecontacto2);
        email=findViewById(R.id.email);

        Intent intent=getIntent();
        Contacto contacto=intent.getExtras().getParcelable(Contacto.KEY);

        img_contacto.setImageResource(contacto.getImagencontacto());
        nombre.setText(contacto.getNombre());
        //email.setText(contacto.getEmail());

    }
}
