package unilim.info.ihm.tp6.exo3.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            cards.add(new Card(i));
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null; // Handle empty deck (optional)
        }
        return cards.remove(0);
    }
}
