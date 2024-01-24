package com.example.jujutsuukaisenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.jujutsuukaisenfinal.databinding.FragmentNuevoElemento2Binding;

public class NuevoElemento2Fragment extends Fragment {

    private FragmentNuevoElemento2Binding binding;
    private Elementos2ViewModel elementos2ViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNuevoElemento2Binding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        elementos2ViewModel = new ViewModelProvider(requireActivity()).get(Elementos2ViewModel.class);
        NavController navController = Navigation.findNavController(view);

        setupTipoSpinner();

        binding.crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre2.getText().toString();
                String descripcion = binding.descripcion2.getText().toString();

                // Verificar la nulidad del tipo seleccionado
                String tipo = binding.tipoSpinner.getSelectedItem() != null
                        ? binding.tipoSpinner.getSelectedItem().toString()
                        : "";

                // Obtener el ID del recurso de la imagen asociada al tipo seleccionado
                int imagenResId = obtenerImagenResIdPorTipo(tipo);

                elementos2ViewModel.insertar(new Elemento2(nombre, descripcion, tipo, imagenResId));

                navController.popBackStack();
            }
        });
    }

    private void setupTipoSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.tipos_array,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.tipoSpinner.setAdapter(adapter);
    }

    private int obtenerImagenResIdPorTipo(String tipo) {
        // Lógica para asignar el ID del recurso de imagen según el tipo seleccionado
        // Aquí deberías implementar la lógica según tus necesidades
        // En este ejemplo, simplemente devuelve 0 como un valor predeterminado.
        return 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
