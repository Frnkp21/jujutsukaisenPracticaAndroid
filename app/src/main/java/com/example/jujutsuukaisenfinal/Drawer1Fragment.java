package com.example.jujutsuukaisenfinal;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jujutsuukaisenfinal.databinding.FragmentDrawer1Binding;
import com.example.jujutsuukaisenfinal.databinding.ViewholderContenidoBinding;

import java.util.List;

public class Drawer1Fragment extends Fragment {
    private FragmentDrawer1Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentDrawer1Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toolbar mainToolbar = requireActivity().findViewById(R.id.toolbar);
        mainToolbar.setTitle("Buscar");

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
        // contenidosAdapter.setOnItemClickListener(contenido -> abrirDetallesFragment(contenido));
        binding.recyclerviewContenidos.setAdapter(contenidosAdapter);

        // Utiliza la instancia del ViewModel creada
        personajesViewModel.respuestaMutableLiveData.observe(getViewLifecycleOwner(), new Observer<Personajes.Respuesta>() {
            @Override
            public void onChanged(Personajes.Respuesta respuesta) {
                if (respuesta != null) {
                    contenidosAdapter.establecerListaContenido(respuesta.documents);
                }
            }
        });
    }
}
