package creazioneConvoglio;

import java.util.Objects;

public class Dotazione implements Comparable<Dotazione>{
    private final String name;
    private final int qty;

    /**
     * <p>
     * Post-condizioni: restituisce una nuova istanza di Dotazione, lancia
     * NullPointerException se name o qty è null
     */
    public Dotazione(final String name, final int qty) {
        this.name = Objects.requireNonNull(name);
        this.qty = Objects.requireNonNull(qty);
    }

    /**
     * <p>Post-condizioni: restituisce la dotazione corrispondente alla string in input
     */
    public static Dotazione parseDotazione(String s){
        String[] dotazione = s.split(" ");
        if(dotazione.length != 2) throw new IllegalArgumentException("La stringa non corrisponde ad una dotazione valida");
        try{
            return new Dotazione(dotazione[0], Integer.parseInt(dotazione[1]));
        }
        catch(NumberFormatException e ){
            throw new NumberFormatException("La quantità è formattata in maniera sbagliata");
        }
    }

    /**
     * <p>
     * Post-condizioni: restituisce il nome della dotazione
     */
    public String getName() {
        return this.name;
    }

    /**
     * <p>
     * Post-condizioni: restituisce la quantità della dotazione
     */
    public int getQuantity() {
        return this.qty;
    }

    /**
     * L'hashCode di una dotazione è l'hashcode della stringa composta dalla stringa
     * corrispondente alla quantità concatenata al nome
     */
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * Due dotazioni sono uguali se hanno stesso nome e stessa quantità
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dotazione))
            return false;
        Dotazione d = (Dotazione) obj;
        return d.name.equals(this.name);
    }

    @Override
    public String toString() {
        return this.name+" = "+this.qty;
    }

    @Override
    public int compareTo(Dotazione o) {
        return this.name.compareTo(o.name);
    }
    
}