package com.example.jujutsuukaisenfinal;

import java.util.ArrayList;
import java.util.List;

public class Elementos2Repositorio {

    List<Elemento2> elementos2 = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Elemento2> elementos2);
    }

    Elementos2Repositorio(){
        elementos2.add(new Elemento2("Suguru Geto", "Suguru Geto (夏油傑 Getō Suguru?) es uno de los principales antagonistas de la serie manga Tokyo Metropolitan Curse Technical School y de la serie secuela, Jujutsu Kaisen. Es uno de los cuatro chamanes de Clase Especial, antiguo estudiante de Masamichi Yaga y compañero de Satoru Gojo y Shoko Ieiri.","Especial",R.drawable.getoo));
        elementos2.add(new Elemento2("Ryomen Sukuna", "Ryomen Sukuna (両面宿儺 Ryōmen Sukuna?) (lit. Sukuna de la Doble Cara), más comúnmente conocido como Sukuna (宿儺 Sukuna?) es el antagonista principal de la serie manga Jujutsu Kaisen. Apodado Rey de las Maldiciones (呪いの王 Noroi no Ō?), fue el chamán más fuerte de hace mil años y actualmente una encarnación de objetos malditos de grado especial.","Especial",R.drawable.sukuna));
        elementos2.add(new Elemento2("Yuuta Okkotsu", "Yuta Okkotsu (乙骨憂太 Okkotsu Yūta?) es el protagonista de la serie manga Tokyo Metropolitan Curse Technical School y uno de los personajes de la serie secuela, Jujutsu Kaisen. Es uno de los cuatro chamanes de Clase Especial y estudiante de segundo año del Colegio Técnico de Magia Metropolitana de Tokio, compañero de Maki Zenin, Panda y Toge Inumaki. Se encontraba en una misión con Miguel en Kenia[4], pero tras el incidente en Shibuya, regresó a Japón[5].","Especial",R.drawable.yuta));
        elementos2.add(new Elemento2("Toji Fushiguro", "Toji Fushiguro (伏黒甚爾 Fushiguro Tōji?), nacido como Toji Zenin (禪院甚爾 Zen'in Tōji?), es un personaje secundario recurrente de Jujutsu Kaisen. Fue miembro del Clan Zenin y el padre de Megumi Fushiguro y Tsumiki Fushiguro.","Especial",R.drawable.toji));
        elementos2.add(new Elemento2("Yuji Itadori", "Yuji Itadori (虎杖悠仁 Itadori Yūji?) es el protagonista de la serie manga Jujutsu Kaisen. Es un estudiante de primer año del Colegio Técnico de Magia Metropolitana de Tokio y recipiente del Rey de las Maldiciones, Sukuna.","Categoria 1",R.drawable.itadori));
        elementos2.add(new Elemento2("Maki Zenin", "Maki Zenin (禪院真希 Zen'in Maki?) es uno de los personajes de la serie manga Tokyo Metropolitan Curse Technical School y su serie secuela, Jujutsu Kaisen. Es una estudiante de segundo año del Colegio Técnico de Magia Metropolitana de Tokio. Forma parte de la familia principal del Clan Zenin, una de los tres clanes de élite de los chamanes y es hermana gemela de Mai Zenin." ,"Categoria 2",R.drawable.maki));
    }

    List<Elemento2> obtener() {
        return elementos2;
    }

    void insertar(Elemento2 elemento2, Callback callback){
        elementos2.add(elemento2);
        callback.cuandoFinalice(elementos2);
    }

    void eliminar(Elemento2 elemento2, Callback callback) {
        elementos2.remove(elemento2);
        callback.cuandoFinalice(elementos2);
    }

    void actualizar(Elemento2 elemento2, String tipo, Callback callback) {
        elemento2.setTipo(tipo);
        callback.cuandoFinalice(elementos2);
    }
}
