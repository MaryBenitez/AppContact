package com.example.maris.appmaryjencontact;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ContactoAdapter adapter;
    ArrayList<Contacto> contacto;
    ArrayList<Contacto> contacto2;
    LinearLayoutManager lm;
    Button contactos;
    Button favoritos;

    CircleImageView imagenc;
    TextView nombrec;
    TextView direccion;
    ImageButton compartir;
    ImageButton llamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacto = new ArrayList<>();
        contacto2 = new ArrayList<>();
        contactos = findViewById(R.id.btncontac);
        favoritos = findViewById(R.id.btnfavorite);

        imagenc = (CircleImageView) findViewById(R.id.foto);
        nombrec = (TextView) findViewById(R.id.nombrecontacto);
        direccion = (TextView) findViewById(R.id.direccion);
        compartir = (ImageButton) findViewById(R.id.btn_share);
        llamada = (ImageButton) findViewById(R.id.btn_call);

        contactos.setOnClickListener(this);
        llamada.setOnClickListener(this);

        rv=findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        adapter=new ContactoAdapter(contacto,this);

        list_contactos = new ArrayList<>();
        list_contactos.add(new Contacto("PRIMER EJEMPLO","PRIMERA DIRECCION",77777777,R.drawable.contact));
        list_contactos.add(new Contacto("SEGUNDO EJEMPLO","SEGUNDO DIRECCION",77777778,R.drawable.contact));
        list_contactos.add(new Contacto("TERCERO EJEMPLO","TERCERO DIRECCION",77777779,R.drawable.contact));

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this,list_contactos);
        rv.setLayoutManager(new GridLayoutManager(this,1));
        rv.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btncontac:
                ObtenerContactos();
                break;
            case R.id.btn_call:
                ObtenerLlamada();
                break;
            default:
                break;
        }
    }

    public void ObtenerLlamada(){
        Uri u;

        u=Uri.parse("content://call_log/call");
        String[] projeccion = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};

        Cursor c=getContentResolver().query(u,projeccion,null,null,null);
        nombrec.setText("");

        while (c.moveToNext()){
            nombrec.append("Tipo: "+c.getString(0)+"Numero: "+c.getString(1)+"Duracion: "+c.getString(2)+"\n");
        }

        c.close();
    }

    public void ObtenerContactos(){


        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };
        String selectionClause = ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";
        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c=getContentResolver().query(ContactsContract.Data.CONTENT_URI,projeccion,selectionClause,null,sortOrder);
        nombrec.setText("");

        while (c.moveToNext()){
            nombrec.append("Nombre: "+c.getString(0)+"Doreccion: "+c.getString(1)+"Numero: "+c.getString(2)+"\n");
        }

        c.close();
    }

    public void CONTACTOS(View view){
        adapter.setF();
        adapter=new ContactoAdapter(contacto,view.getContext());
        rv.setAdapter(adapter);
    }
    public void FAVORITO(View view){
        adapter.setT();
        adapter=new ContactoAdapter(contacto2,view.getContext());
    }

    public void agregar_favorito(Contacto list_fav){
        contacto2.add(list_fav);
    }
    public void eliminar_favorito(String no_list_fav){
        int cont=0;
        for(Contacto contacto : contacto2){
            if (contacto.getNombre() == no_list_fav){
                break;;
            }
            cont++;
        }
        contacto2.remove(cont);
        if (adapter.Addfavorito()){
            adapter=new ContactoAdapter(contacto2,this);
            rv.setAdapter(adapter);
        }
    }


}
