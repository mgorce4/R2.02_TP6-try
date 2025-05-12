package unilim.info.ihm.tp6.exo3.tools;

import java.util.Random;

public class CardGameTools {
	
	public static Integer generateCardValue() {
        Random random = new Random();
        return random.nextInt(12) + 1;
	}
	
	public static void loadCardImage(Integer value) {
		if (value > 0 && value < 13) {
			System.out.println("la carte numero " + value + " à été chargé");
		} else {
			System.out.println("veuillez choisir un nombre entre 1 et 12");
		}
	}

}
