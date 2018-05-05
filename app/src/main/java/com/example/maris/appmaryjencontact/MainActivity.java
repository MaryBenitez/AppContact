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

    public MainActivity(RecyclerView rv, ContactoAdapter adapter, ArrayList<Contacto> contacto, ArrayList<Contacto> contacto2, LinearLayoutManager lm, Button contactos, Button favoritos) {
        this.rv = rv;
        this.adapter = adapter;
        this.contacto = contacto;
        this.contacto2 = contacto2;
        this.lm = lm;
        this.contactos = contactos;
        this.favoritos = favoritos;
    }

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
        compartir = (ImageButton) findViewById(R.id.btn_compartir);
        llamada = (ImageButton) findViewById(R.id.btn_llamar);

        rv=findViewById(R.id.recycler);
        rv.setHasFixedSize(true);

        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        adapter=new ContactoAdapter(contacto,this);

        //EXTRASHENDO CONTACTOS

        ArrayList<String> nombres_contacto = new ArrayList<String>();
        ArrayList<String> numeros_contacto = new ArrayList<String>();

        String[] projeccion = new String[] { ContactsContract.Data._ID, ContactsContract.Data.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.TYPE };

        String selectionClause = ContactsContract.Data.MIMETYPE + "='" +
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE + "' AND "
                + ContactsContract.CommonDataKinds.Phone.NUMBER + " IS NOT NULL";

        String sortOrder = ContactsContract.Data.DISPLAY_NAME + " ASC";

        Cursor c = getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                projeccion,
                selectionClause,
                null,
                sortOrder);

        while(c.moveToNext()){
            nombres_contacto.add(c.getString(1));
            numeros_contacto.add(c.getString(2));
        }
        c.close();

        Bundle datos = new Bundle();
        datos.putStringArrayList("Nombre",nombres_contacto);
        datos.putStringArrayList("Numero",numeros_contacto);

        ContactoFragment fragmentContact = new ContactoFragment();
        fragmentContact.setArguments(datos);

    }


    public void CONTACTOS(View view){
        adapter.setF();
        adapter=new ContactoAdapter(contacto, (MainActivity) view.getContext());
        rv.setAdapter(adapter);
    }
    public void FAVORITO(View view){
        adapter.setT();
        adapter=new ContactoAdapter(contacto2, (MainActivity) view.getContext());
    }

    public void agregar_favorito(Contacto list_fav){
        contacto2.add(list_fav);
    }
    public void eliminar_favorito(String no_list_fav){
        int cont=0;
        for(Contacto contacto : contacto2){
            if (contacto.getNombre() == no_list_fav){
                break;
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
