package com.example.jujutsuukaisenfinal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class Personajes {
    class Respuesta {
        List<Contenido> documents;
    }

    class Contenido {
        Fields fields;

    }
    class Fields{
        Field personajeName;
        Field description;
        Field imgurl;
    }
    class Field{
        String stringValue;
    }
    public static Api api = new Retrofit.Builder()
            .baseUrl("https://firestore.googleapis.com/v1/projects/retrofit-84b5f/databases/(default)/documents/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);

    public interface Api {
        @GET("Personajes")
        Call<Respuesta> buscar();
    }
}
