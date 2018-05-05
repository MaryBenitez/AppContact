package com.example.maris.appmaryjencontact;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class ContactoFragment extends Fragment{

    View view;
    private RecyclerView rv2;
    private List<Contacto> leerContacto;
    private EditText busq;
    private ImageView imagec;

    public ContactoFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayList<String> nombres_contacto = getArguments().getStringArrayList("Nombre");
        ArrayList<String> email_contacto = getArguments().getStringArrayList("Email");
        ArrayList<String> numeros_contacto = getArguments().getStringArrayList("Numeros");
        ArrayList<String> img = getArguments().getStringArrayList("Imagen");


        leerContacto = new ArrayList<>();
        int i = 0;
        while (i < nombres_contacto.size()) {
            leerContacto.add(new Contacto(nombres_contacto.get(i), email_contacto.get(i), numeros_contacto.get(i),Integer.parseInt(img.get(i)),img.get(i)));
            i++;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_main, container, false);
        rv2 = view.findViewById(R.id.recycler);

        ContactoAdapter contactoAdapter = new ContactoAdapter(getContext(),leerContacto);
        rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv2.setAdapter(contactoAdapter);

        busq = view.findViewById(R.id.busqueda);
        busq.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<Contacto> filteredList = new ArrayList<>();
                for(Contacto item : leerContacto){
                    if(item.getNombre().toLowerCase().contains(s.toString().toLowerCase())){
                        filteredList.add(item);
                    }
                }
                ContactoAdapter cA = new ContactoAdapter(getContext(),filteredList);
                rv2.setLayoutManager(new LinearLayoutManager(getActivity()));
                rv2.setAdapter(cA);
            }
        });


        return view;

    }
}
