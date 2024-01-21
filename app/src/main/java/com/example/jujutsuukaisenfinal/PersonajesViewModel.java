package com.example.jujutsuukaisenfinal;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonajesViewModel extends AndroidViewModel {

    MutableLiveData<Personajes.Respuesta> respuestaMutableLiveData = new MutableLiveData<>();

    public PersonajesViewModel(@NonNull Application application) {
        super(application);
    }

    public void buscar(String texto){
        Personajes.api.buscar(/*texto*/).enqueue(new Callback<Personajes.Respuesta>() {
            @Override
            public void onResponse(@NonNull Call<Personajes.Respuesta> call, @NonNull Response<Personajes.Respuesta> response) {
                respuestaMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Personajes.Respuesta> call, @NonNull Throwable t) {}
        });
    }
}