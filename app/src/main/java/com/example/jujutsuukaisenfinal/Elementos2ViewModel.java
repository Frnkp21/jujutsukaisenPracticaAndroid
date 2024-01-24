package com.example.jujutsuukaisenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Elementos2ViewModel extends AndroidViewModel {

    Elementos2Repositorio elementos2Repositorio;

    MutableLiveData<List<Elemento2>> listElementos2MutableLiveData = new MutableLiveData<>();
    MutableLiveData<Elemento2> elemento2Seleccionado = new MutableLiveData<>();

    public Elementos2ViewModel(@NonNull Application application) {
        super(application);

        elementos2Repositorio = new Elementos2Repositorio();

        listElementos2MutableLiveData.setValue(elementos2Repositorio.obtener());
    }

    MutableLiveData<List<Elemento2>> obtenerElementos2() {
        return listElementos2MutableLiveData;
    }

    void insertar(Elemento2 elemento2) {
        elementos2Repositorio.insertar(elemento2, new Elementos2Repositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Elemento2> elementos2) {
                listElementos2MutableLiveData.setValue(elementos2);
            }
        });
    }

    void eliminar(Elemento2 elemento2) {
        elementos2Repositorio.eliminar(elemento2, new Elementos2Repositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Elemento2> elementos2) {
                listElementos2MutableLiveData.setValue(elementos2);
            }
        });
    }

    void actualizar(Elemento2 elemento2, String tipo) {
        elementos2Repositorio.actualizar(elemento2, tipo, new Elementos2Repositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Elemento2> elementos2) {
                listElementos2MutableLiveData.setValue(elementos2);
            }
        });
    }

    void seleccionar(Elemento2 elemento2) {
        elemento2Seleccionado.setValue(elemento2);
    }

    MutableLiveData<Elemento2> seleccionado() {
        return elemento2Seleccionado;
    }

    public LiveData<List<Elemento2>> obtenerElementos2MutableLiveData() {
        return listElementos2MutableLiveData;
    }
}
