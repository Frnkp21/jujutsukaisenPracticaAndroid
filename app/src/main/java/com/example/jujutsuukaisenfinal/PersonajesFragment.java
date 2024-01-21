package com.example.jujutsuukaisenfinal;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jujutsuukaisenfinal.databinding.FragmentPersonajesBinding;
import com.example.jujutsuukaisenfinal.databinding.ViewholderContenidoBinding;

import java.util.ArrayList;
import java.util.List;

public class PersonajesFragment extends Fragment {
    private FragmentPersonajesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentPersonajesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PersonajesViewModel personajesViewModel = new ViewModelProvider(this).get(PersonajesViewModel.class);

        binding.texto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) { return false; }

            @Override
            public boolean onQueryTextChange(String s) {
                personajesViewModel.buscar(s);
                return false;
            }
        });
        ContenidosAdapter contenidosAdapter = new ContenidosAdapter();
        binding.recyclerviewContenidos.setAdapter(contenidosAdapter);

        personajesViewModel.respuestaMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Personajes.Respuesta>() {
            @Override
            public void onChanged(Personajes.Respuesta respuesta) {
                    contenidosAdapter.establecerListaContenido(respuesta.documents);
                }
                //respuesta.results.forEach(contenido -> Log.e("ABCD", contenido.artistName + ", " + contenido.trackName + ", " + contenido.artworkUrl100));
        });

    }
    static class PersonajesViewHolder extends RecyclerView.ViewHolder {
        ViewholderContenidoBinding binding;

        public PersonajesViewHolder(@NonNull ViewholderContenidoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    class ContenidosAdapter extends RecyclerView.Adapter<PersonajesViewHolder>{
        List<Personajes.Contenido> contenidoList = new ArrayList<>();

        @NonNull
        @Override
        public PersonajesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PersonajesViewHolder(ViewholderContenidoBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PersonajesViewHolder holder, int position) {
            Personajes.Contenido contenido = contenidoList.get(position);

            holder.binding.description.setText(contenido.fields.description.stringValue);
            holder.binding.personajes.setText(contenido.fields.personajeName.stringValue);
            Glide.with(requireActivity()).load(contenido.fields.imgurl.stringValue).into(holder.binding.imgurl);
        }

        @Override
        public int getItemCount() {
            return contenidoList == null ? 0 : contenidoList.size();
        }

        void establecerListaContenido(List<Personajes.Contenido> contenidoList){
            this.contenidoList = contenidoList;
            notifyDataSetChanged();
        }
    }
}