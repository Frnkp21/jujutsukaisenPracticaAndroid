package com.example.jujutsuukaisenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jujutsuukaisenfinal.databinding.FragmentDrawer3Binding;
import com.example.jujutsuukaisenfinal.databinding.ViewholderElemento2Binding;

import java.util.List;

public class Drawer3Fragment extends Fragment {

    private FragmentDrawer3Binding binding;
    private Elementos2ViewModel elemento2ViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentDrawer3Binding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        elemento2ViewModel = new ViewModelProvider(requireActivity()).get(Elementos2ViewModel.class);
        navController = Navigation.findNavController(view);

        binding.irANuevoElemento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_nuevoElemento2Fragment);
            }
        });

        Elementos2Adapter elementos2Adapter = new Elementos2Adapter();
        binding.recyclerView.setAdapter(elementos2Adapter);

        elemento2ViewModel.obtenerElementos2MutableLiveData().observe(getViewLifecycleOwner(), new Observer<List<Elemento2>>() {
            @Override
            public void onChanged(List<Elemento2> Elemento2) {
                elementos2Adapter.establecerLista(Elemento2);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Elemento2 elemento2 = elementos2Adapter.obtenerElemento2(posicion);
                elemento2ViewModel.eliminar(elemento2);
            }
        }).attachToRecyclerView(binding.recyclerView);
    }

    class Elemento2ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderElemento2Binding binding;

        public Elemento2ViewHolder(ViewholderElemento2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class Elementos2Adapter extends RecyclerView.Adapter<Elemento2ViewHolder> {

        List<Elemento2> elemento2;

        @NonNull
        @Override
        public Elemento2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new Elemento2ViewHolder(ViewholderElemento2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull Elemento2ViewHolder holder, int position) {
            Elemento2 elemento2 = this.elemento2.get(position);

            holder.binding.nombre2.setText(elemento2.nombre2);
            holder.binding.tipoMostrado.setText(elemento2.tipo);

            // Carga la imagen asociada al elemento en el ImageView
            int imagenResId = elemento2.getImagenResId();
            if (imagenResId != 0) {
                holder.binding.imagenElemento.setImageResource(imagenResId);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    elemento2ViewModel.seleccionar(elemento2);
                    navController.navigate(R.id.action_mostrarElemento2Fragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return elemento2 != null ? elemento2.size() : 0;
        }

        public void establecerLista(List<Elemento2> elemento2){
            this.elemento2 = elemento2;
            notifyDataSetChanged();
        }

        public Elemento2 obtenerElemento2(int posicion){
            return elemento2.get(posicion);
        }
    }

}
