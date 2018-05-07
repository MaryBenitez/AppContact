package com.example.maris.appmaryjencontact;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>{

    private ArrayList<Contacto> contacto;
    private Context context;
    private static boolean favorito = false;

    public ContactoAdapter(Context context, ArrayList<Contacto> contacto){
        this.context=context;
        this.contacto=contacto;
    }

    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardviewcontact,parent,false);

        return new ContactoViewHolder(view);
    }

    public void onBindViewHolder(final ContactoViewHolder holder, final int position){

        holder.imagen_de_contacto.setImageResource(contacto.get(position).getImagencontacto());
        holder.nombre.setText((contacto.get(position).getNombre()));
        holder.boton_informacion.setImageResource(R.drawable.informacion);

        //Leyendo el click en las cardview
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(context,ContactoActivity.class);

                //pasando datos para ContactoActivity
                intent.putExtra("Nombre",contacto.get(position).getNombre());
                intent.putExtra("Numero",contacto.get(position).getNumero());
                intent.putExtra("Email",contacto.get(position).getEmail());
                intent.putExtra("Imagen",contacto.get(position).getImagencontacto());

                //Iniciando la actividad
                context.startActivity(intent);

            }
        });


        //SECCION DE FAVORITOS
        if (contacto.get(position).isLista_favoritos()){
            holder.boton_favorito.setImageResource(R.drawable.fav_mar);
        }
        else {
            holder.boton_favorito.setImageResource(R.drawable.fav);
        }

        holder.boton_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Listafavorito(position)){
                    holder.boton_favorito.setImageResource(R.drawable.fav_mar);
                    ((MainActivity)context).agregar_favorito(contacto.get(position));
                    Toast.makeText(view.getContext(),"Agregado a Favorito",Toast.LENGTH_SHORT).show();
                }
                else {
                    holder.boton_favorito.setImageResource(R.drawable.fav);
                    Toast.makeText(view.getContext(),"Eliminado Favorito",Toast.LENGTH_SHORT).show();
                    ((MainActivity)context).eliminar_favorito(contacto.get(position).getNombre());
                }
            }
        });

    }

    @Override
    public int getItemCount(){
        return contacto.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        CircleImageView imagen_de_contacto;
        TextView nombre;
        ImageButton boton_informacion;
        ImageButton boton_favorito;

        public ContactoViewHolder(View itemView){
            super(itemView);

            cardView=itemView.findViewById(R.id.card_view);
            imagen_de_contacto=itemView.findViewById(R.id.foto);
            nombre=itemView.findViewById(R.id.nombrecontacto);
            boton_informacion=itemView.findViewById(R.id.btn_informacion);
            boton_favorito=itemView.findViewById(R.id.btn_favorito);

        }

    }

    //Busqueda
    public void Busqueda(ArrayList<Contacto> busqueda) {
        contacto = busqueda;
        notifyDataSetChanged();
    }

    public boolean Listafavorito(int posicion){
        contacto.get(posicion).setLista_favoritos(!contacto.get(posicion).isLista_favoritos());
        return contacto.get(posicion).isLista_favoritos();
    }

    //para botones principales
    public void setT(){
        favorito = true;
    }
    public void setF(){
        favorito = false;
    }

    //Metodo utilizado en favoritos
    public boolean Addfavorito(){
        return favorito;
    }



}
