package com.example.maris.appmaryjencontact;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacto = new ArrayList<>();
        contacto2 = new ArrayList<>();
        contactos = findViewById(R.id.btncontac);
        favoritos = findViewById(R.id.btnfavorite);

        addContacts();

        rv=findViewById(R.id.recycler);
        adapter=new ContactoAdapter(this, contacto);
        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

    }

    public void addContacts() {
        try {
            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");
            while (phones.moveToNext()) {
                String nombre = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String email = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                String numero = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Log.d("LEL",nombre+" " +numero);
                contacto.add(new Contacto(nombre,email,numero,R.drawable.contact));
            }
            phones.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                addContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /*public void ContactosQuemados(){
        String TAG = "Mensaje";
        contacto = new ArrayList<>();

        contacto.add(new Contacto("Marisol Benitez\n","00094716@uca.edu.sv\n","77951321",R.drawable.contact));
        contacto.add(new Contacto("Marisol D\n","00094716@uca.edu.sv\n","77951321",R.drawable.contact));
        contacto.add(new Contacto("Marisol E\n","00094716@uca.edu.sv\n","77951321",R.drawable.contact));
        contacto.add(new Contacto("Marisol V\n","00094716@uca.edu.sv \n","77951321",R.drawable.contact));

    }*/

    public void CONTACTOS(View view){
        adapter.setF();
        adapter=new ContactoAdapter(view.getContext(), contacto);
        rv.setAdapter(adapter);
    }
    public void FAVORITO(View view){
        adapter.setT();
        adapter=new ContactoAdapter(view.getContext(), contacto2);
        rv.setAdapter(adapter);
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
            adapter=new ContactoAdapter(this, contacto2);
            rv.setAdapter(adapter);
        }
    }


}
