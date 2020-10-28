package creazioneConvoglio;

public class Locomotore extends Rotabile {
    public final int potenza;

    public Locomotore(String modello, int peso, int potenza) {
        super(modello, peso);
        if (potenza < 1)
            throw new IllegalArgumentException("La potenza deve essere maggiore di 0");
        this.potenza = potenza;
    }

    /**
     * <p>
     * Post-condizioni: restituisce un nuovo Locomotore corrispondente alla stringa
     * in input (formato stringa: Nome Peso Potenza)
     * <p>
     * throws IllegalArgumentException se la stringa non corrisponde ad un
     * locomotore valido
     * <p>
     * throws NumberFormatException se il peso/potenza è nel formato sbagliato
     */
    public static Locomotore parseLocomotore(String s) {
        String[] locomotore = s.split(" ");

        if (locomotore.length != 4)
            throw new IllegalArgumentException("La stringa non corrisponde ad un locomotore valido");

        try {
            return new Locomotore(locomotore[1], Integer.parseInt(locomotore[2]), Integer.parseInt(locomotore[3]));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Formattazione del peso/potenza errato");
        }
    }

    @Override
    public String toString() {
        return "Locomotore: modello = " + this.modello + ", peso = " + this.peso + ", potenza = " + this.potenza;
    }

    @Override
    public boolean isLocomotore() {
        return true;
    }

    @Override
    public boolean isRotabile() {
        return false;
    }

}
