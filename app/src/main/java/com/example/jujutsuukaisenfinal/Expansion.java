package com.example.jujutsuukaisenfinal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.lifecycle.LiveData;

public class Expansion {
    LiveData<String> ordenLiveData = new LiveData<String>() {
        @Override
        protected void onActive() {
            super.onActive();

            iniciarExpansion(new ExpansionListener() {
                @Override
                public void cuandoDeLaOrden(String orden) {
                    postValue(orden);
                }
            });
        }

        @Override
        protected void onInactive() {
            super.onInactive();

            pararEntrenamiento();
        }
    };
    interface ExpansionListener {
        void cuandoDeLaOrden(String orden);
    }

    Random random = new Random();
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> expandiendo;

    void iniciarExpansion(ExpansionListener expansionListener) {
        if (expandiendo == null || expandiendo.isCancelled()) {
            expandiendo = scheduler.scheduleAtFixedRate(new Runnable() {
                int expansion;
                int repeticiones = -1;

                @Override
                public void run() {
                    if (repeticiones < 0) {
                        repeticiones = random.nextInt(3) + 3;
                        expansion = random.nextInt(5)+1;
                    }
                    expansionListener.cuandoDeLaOrden("Fase" + expansion + ":" + (repeticiones == 0 ? "Expansion de dominio" : repeticiones));
                    repeticiones--;
                }
            }, 0, 1, SECONDS);
        }
    }

    void pararEntrenamiento() {
        if (expandiendo != null) {
            expandiendo.cancel(true);
        }
    }
}