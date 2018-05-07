package com.example.maris.appmaryjencontact;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        imgcontact=findViewById(R.id.foto);
        nombrec=findViewById(R.id.nombrecontactoA);
        emailc=findViewById(R.id.emailcA);
        numeroc=findViewById(R.id.numeroA);

        compartir=findViewById(R.id.compartir);

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

    //Metodo compartir
    public void compartir(View v){
        Intent sendIntent = null, chooser =null;

            //Uri imageUri = Uri.parse("android.resource://com.maris.appmaryjencontact/drawable/"+R.drawable.contact);

            sendIntent= new Intent(Intent.ACTION_SEND);
        //sendIntent.setType("image/*");
        //sendIntent.putExtra(Intent.EXTRA_STREAM,imageUri);

        sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Nombre: "+((TextView)findViewById(R.id.nombrecontactoA)).getText().toString()+"\n"+
                                                         "Email: "+((TextView) findViewById(R.id.emailcA)).getText().toString()+"\n"+
                                                         "Numero: "+((TextView) findViewById(R.id.numeroA)).getText().toString());

        sendIntent.setType("text/plain");
        Intent.createChooser(sendIntent,"compartir");
        startActivity(sendIntent);

    }

}
