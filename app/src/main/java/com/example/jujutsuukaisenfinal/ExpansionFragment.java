package com.example.jujutsuukaisenfinal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.jujutsuukaisenfinal.databinding.FragmentExpansionBinding;

public class ExpansionFragment extends Fragment {
    private FragmentExpansionBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentExpansionBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExpansionViewModel expansionViewModel = new ViewModelProvider(this).get(ExpansionViewModel.class);

        expansionViewModel.obtenerExpansion().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer ejercicio) {
                Glide.with(ExpansionFragment.this).load(ejercicio).into(binding.expansion);
            }
        });

        expansionViewModel.obtenerRepeticion().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String repeticion) {
                if(repeticion.equals("CAMBIO")){
                    binding.cambio.setVisibility(View.VISIBLE);
                } else {
                    binding.cambio.setVisibility(View.GONE);
                }
                binding.repeticion.setText(repeticion);
            }
        });
    }
}