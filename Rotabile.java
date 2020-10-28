package creazioneConvoglio;

import java.util.Objects;

public abstract class Rotabile {
    public final String modello;
    public final int peso;

    public Rotabile(String modello, int peso){
        if(peso < 1) throw new IllegalArgumentException("Il peso deve essere maggiore di 0");
        this.modello = Objects.requireNonNull(modello);
        this.peso = peso;
    }
    public abstract boolean isLocomotore();
    public abstract boolean isRotabile();
}
