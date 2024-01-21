package com.example.jujutsuukaisenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jujutsuukaisenfinal.databinding.FragmentNuevoElemento2Binding;

public class NuevoElemento2Fragment extends Fragment {

    private FragmentNuevoElemento2Binding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentNuevoElemento2Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Elementos2ViewModel elementos2ViewModel = new ViewModelProvider(requireActivity()).get(Elementos2ViewModel.class);
        NavController navController = Navigation.findNavController(view);
        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre2.getText().toString();
                String descripcion = binding.descripcion2.getText().toString();

                elementos2ViewModel.insertar(new Elemento2(nombre, descripcion));

                navController.popBackStack();
            }
        });

    }
}