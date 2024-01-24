package com.example.jujutsuukaisenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.jujutsuukaisenfinal.databinding.FragmentMostrarElemento2Binding;



public class MostrarElemento2Fragment extends Fragment {
    private FragmentMostrarElemento2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentMostrarElemento2Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Elementos2ViewModel elementos2ViewModel = new ViewModelProvider(requireActivity()).get(Elementos2ViewModel.class);
        elementos2ViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Elemento2>() {
            @Override
            public void onChanged(Elemento2 elemento2) {
                binding.nombre2.setText(elemento2.nombre2);
                binding.descripcion2.setText(elemento2.descripcion2);
                binding.tipoMostrado.setText("Tipo: " + elemento2.getTipo());

                // Cargar y establecer la imagen en el ImageView
                binding.imagenElemento.setImageResource(elemento2.getImagenResId());
            }
        });
    }
}

