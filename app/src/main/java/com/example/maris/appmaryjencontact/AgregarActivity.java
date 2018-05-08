package com.example.maris.appmaryjencontact;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class AgregarActivity extends AppCompatActivity {

    private CircleImageView imgcontactN;
    private EditText nombreA;
    private EditText emailA;
    private EditText numeroA;

    private Contacto contacto;
    public static final String EXTRA_CONTACT = "EXTRA_CONTACT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        imgcontactN=findViewById(R.id.foto);
        nombreA=findViewById(R.id.addname);
        emailA=findViewById(R.id.addemail);
        numeroA=findViewById(R.id.addnum);

        //Recibiendo datos
        Intent intent = getIntent();
        contacto = intent.hasExtra(EXTRA_CONTACT)?(Contacto)intent.getParcelableExtra(EXTRA_CONTACT): (new Contacto());

        //Seteando datos
        nombreA.setText(contacto.getNombre());
        emailA.setText(contacto.getEmail());
        numeroA.setText(contacto.getNumero());
        imgcontactN.setImageResource(R.drawable.contact);

    }

    //Metodo para agregar
    public void agregar_contacto(View view){

        contacto.setImagencontacto(R.drawable.contact);
        contacto.setNombre(nombreA.getText().toString());
        contacto.setEmail(emailA.getText().toString());
        contacto.setNumero(numeroA.getText().toString());

        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_CONTACT, contacto);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();

    }

}
