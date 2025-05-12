package unilim.info.ihm.tp6.exo3.model;

public class Card {
	
    private final Integer value;


    public Card(Integer value) {
        if (value == null || value < 1 || value > 12) {
            throw new IllegalArgumentException("La valeur doit être comprise entre 1 (As) et 12 (Reine)");
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

}
