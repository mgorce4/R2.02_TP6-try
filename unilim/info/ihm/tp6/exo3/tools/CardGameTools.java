package unilim.info.ihm.tp6.exo3.tools;

import javafx.scene.image.Image;

public class CardGameTools {

    private static final String IMAGE_PATH = "/unilim/info/ihm/tp6/exo3/ressources/";

    public static int generateCardValue() {
        return (int) (Math.random() * 12) + 1;
    }

    public static Image loadCardImage(int value) {
        String imageName;
        if (value == 13) {
            imageName = "dos.png";
        } else {
            imageName = Integer.toString(value) + ".png";
        }

        // Chargement via getResource, pour avoir une URL correcte
        return new Image(CardGameTools.class.getResourceAsStream(IMAGE_PATH + imageName));
    }
}
