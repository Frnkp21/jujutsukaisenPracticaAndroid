package com.example.jujutsuukaisenfinal;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.jujutsuukaisenfinal.databinding.ViewholderContenidoBinding;

import java.util.List;

public class ContenidosAdapter extends RecyclerView.Adapter<ContenidosAdapter.ContenidoViewHolder> {
    private List<Personajes.Contenido> contenidoList;
    private OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public ContenidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderContenidoBinding binding = ViewholderContenidoBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ContenidoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContenidoViewHolder holder, int position) {
        Personajes.Contenido contenido = contenidoList.get(position);

        holder.binding.personajes.setText(contenido.fields.personajeName.stringValue);
        holder.binding.description.setText(contenido.fields.description.stringValue);
        Glide.with(holder.itemView.getContext()).load(contenido.fields.imgurl.stringValue).into(holder.binding.imgurl);

        holder.itemView.setOnClickListener(view -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(contenido);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contenidoList == null ? 0 : contenidoList.size();
    }

    void establecerListaContenido(List<Personajes.Contenido> contenidoList){
        this.contenidoList = contenidoList;
        notifyDataSetChanged();
    }

    static class ContenidoViewHolder extends RecyclerView.ViewHolder {
        //private final ViewholderContenidoBinding binding;
        private final ViewholderContenidoBinding binding;

        public ContenidoViewHolder(@NonNull ViewholderContenidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Personajes.Contenido contenido);
    }
}