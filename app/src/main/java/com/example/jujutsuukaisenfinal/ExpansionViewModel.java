package com.example.jujutsuukaisenfinal;

import android.app.Application;

import androidx.annotation.NonNull;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import kotlin.jvm.functions.Function1;

public class ExpansionViewModel extends AndroidViewModel {
    Expansion expansion;

    LiveData<Integer> ejercicioLiveData;
    LiveData<String> repeticionLiveData;

    public ExpansionViewModel(@NonNull Application application) {
        super(application);

        expansion = new Expansion();

        ejercicioLiveData = Transformations.switchMap(expansion.ordenLiveData, new Function1<String, LiveData<Integer>>() {

            String ejercicioAnterior;

            @Override
            public LiveData<Integer> invoke(String orden) {

                String ejercicio = orden.split(":")[0];

                if(!ejercicio.equals(ejercicioAnterior)){
                    ejercicioAnterior = ejercicio;
                    int imagen;
                    switch (ejercicio) {
                        case "EXPANSION1":
                        default:
                            imagen = R.drawable.e5;
                            break;
                        case "Fase2":
                            imagen = R.drawable.yuuta;
                            break;
                        case "Fase3":
                            imagen = R.drawable.mahito;
                            break;
                        case "Fase4":
                            imagen = R.drawable.e2;
                            break;
                    }

                    return new MutableLiveData<>(imagen);
                }
                return null;
            }
        });

        repeticionLiveData = Transformations.switchMap(expansion.ordenLiveData, new Function1<String, LiveData<String>>() {
            @Override
            public LiveData<String> invoke(String orden) {
                return new MutableLiveData<>(orden.split(":")[1]);
            }
        });
    }

    LiveData<Integer> obtenerExpansion(){
        return ejercicioLiveData;
    }

    LiveData<String> obtenerRepeticion(){
        return repeticionLiveData;
    }
}