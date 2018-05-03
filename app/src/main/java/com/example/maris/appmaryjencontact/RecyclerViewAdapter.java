package com.example.maris.appmaryjencontact;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context context;
    private List<Contacto> contactos;

    public RecyclerViewAdapter(Context context, List<Contacto> contactos) {
        this.context = context;
        contactos = contactos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.cardviewcontact,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        holder.imagen_contacto.setImageResource(contactos.get(position).getImagencontacto());
        holder.nombre_contacto.setText(contactos.get(position).getNombre());

    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imagen_contacto;
        TextView nombre_contacto;
        ImageButton boton_favorito;
        Button boton_informacion;

        public MyViewHolder(View itemView) {
            super(itemView);

            imagen_contacto = (CircleImageView) itemView.findViewById(R.id.foto);
            nombre_contacto = (TextView) itemView.findViewById(R.id.nombrecontacto);
            boton_favorito = (ImageButton) itemView.findViewById(R.id.btn_fav);
            boton_informacion = (Button) itemView.findViewById(R.id.btninfo);

        }
    }

}
