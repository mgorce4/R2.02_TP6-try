package unilim.info.ihm.tp6.exo3.view;


import javafx.scene.image.ImageView;
import unilim.info.ihm.tp6.exo3.model.Card;


public class CardImageView extends ImageView {

	private final int value;

	public CardImageView(Card card) {
		super();
		try {
			this.value = card.getValue();
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("La valeur de la carte doit être comprise entre 1 et 12");
		}
		String imagePath = "src/unilim/info/ihm/tp6/exo3/ressources/" + value + ".png";
		setImage(new javafx.scene.image.Image(imagePath));
		
		
	}


}
