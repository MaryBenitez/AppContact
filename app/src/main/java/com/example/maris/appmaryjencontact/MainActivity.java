package com.example.maris.appmaryjencontact;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    ContactoAdapter adapter;
    ArrayList<Contacto> contacto;
    ArrayList<Contacto> contacto2;
    LinearLayoutManager lm;
    Button contactos;
    Button favoritos;

    EditText buscar;


    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacto = new ArrayList<>();
        contacto2 = new ArrayList<>();
        contactos = findViewById(R.id.btncontac);
        favoritos = findViewById(R.id.btnfavorite);

        buscar = findViewById(R.id.busqueda);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        addContacts();

        rv=findViewById(R.id.recycler);
        adapter=new ContactoAdapter(this, contacto){

            @Override
            public void onclickCardView(Contacto contacto) {
                RecibiendoIntent(contacto);
            }

        };
        lm=new LinearLayoutManager(this);
        rv.setLayoutManager(new GridLayoutManager(this,3));
        rv.setAdapter(adapter);
        rv.setHasFixedSize(true);

        //aplicando la busqueda
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Buscar(editable.toString());
            }
        });

    }

    //Metodo para extraer contactos
    public void addContacts() {

        try {

            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC");

            while (phones.moveToNext()) {

                 String nombre = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                 String email = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                 String numero = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                 contacto.add(new Contacto(nombre,email,numero,R.drawable.contact));

            }
            phones.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    //permisos para leer los datos de contacto
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permiso permitido
                addContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //metodos para botones principales
    public void CONTACTOS(View view){
        adapter.setF();

        contactos.setBackgroundColor(getResources().getColor(R.color.select));
        favoritos.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

        adapter=new ContactoAdapter(view.getContext(), contacto){

            @Override
            public void onclickCardView(Contacto contacto) {
                RecibiendoIntent(contacto);
            }

        };
        rv.setAdapter(adapter);
    }
    public void FAVORITO(View view){
        adapter.setT();

        contactos.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        favoritos.setBackgroundColor(getResources().getColor(R.color.select));

        adapter=new ContactoAdapter(view.getContext(), contacto2){

            @Override
            public void onclickCardView(Contacto contacto) {
                RecibiendoIntent(contacto);
            }

        };
        rv.setAdapter(adapter);


    }

    //Metodos para favoritos
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
            adapter=new ContactoAdapter(this, contacto2){

                @Override
                public void onclickCardView(Contacto contacto) {
                    RecibiendoIntent(contacto);
                }

            };
            rv.setAdapter(adapter);
        }
    }

    //Metodo de busqueda
    private void Buscar(String txt){
        ArrayList<Contacto> contacto3 = new ArrayList<>();
        for(Contacto item : contacto){
            if(item.getNombre().toLowerCase().contains(txt.toLowerCase())){
                contacto3.add(item);
            }
        }
        adapter.Busqueda(contacto3);
    }

    public void RecibiendoIntent(Contacto contacto){

        Intent intent = new Intent(getApplicationContext(),ContactoActivity.class);
        intent.putExtra(Contacto.KEY_CONTACT,contacto);
        startActivity(intent);

    }

}
