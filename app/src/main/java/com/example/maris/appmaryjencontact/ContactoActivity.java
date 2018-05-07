package com.example.maris.appmaryjencontact;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactoActivity extends AppCompatActivity {

    private CircleImageView imgcontact;
    private TextView nombrec;
    private TextView emailc;
    private TextView numeroc;

    private ImageButton compartir;
    private ImageButton llamada;

    private String numC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        imgcontact = findViewById(R.id.foto);
        nombrec = findViewById(R.id.nombrecontactoA);
        emailc = findViewById(R.id.emailcA);
        numeroc = findViewById(R.id.numeroA);

        compartir = findViewById(R.id.compartir);
        llamada = findViewById(R.id.llamar);

        //Recibiendo datos
        Intent intent2 = getIntent();
        Contacto contacto = intent2.getExtras().getParcelable(Contacto.KEY_CONTACT);

        //Seteando valores
        nombrec.setText(contacto.getNombre());
        emailc.setText(contacto.getEmail());
        numeroc.setText(contacto.getNumero());
        imgcontact.setImageResource(contacto.getImagencontacto());
        numC = contacto.getNumero();

        llamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Llamada(numC);
            }
        });

    }

    //metodo para compartir
    public void Compartir(View v) {

        //Uri imageUri = Uri.parse("android.resource://com.maris.appmaryjencontact/drawable/"+R.drawable.contact);

        Intent intent = getIntent();
        Contacto contacto = intent.getExtras().getParcelable(Contacto.KEY_CONTACT);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);

        sendIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: " + contacto.getNombre() + "\n" +
                                                     "Email: " + contacto.getEmail() + "\n" +
                                                     "Numero: " + contacto.getNumero());

        sendIntent.setType("text/plain");
        Intent.createChooser(sendIntent, "compartir");
        startActivity(sendIntent);

    }

    public void Llamada(String numero) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + numero));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(intent);

    }

}
