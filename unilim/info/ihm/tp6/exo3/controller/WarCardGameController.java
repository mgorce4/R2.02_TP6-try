package unilim.info.ihm.tp6.exo3.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import unilim.info.ihm.tp6.exo3.tools.CardGameTools;

public class WarCardGameController {

    @FXML
    private Button idBtnChanger;

    @FXML
    private Button idBtnQuitter;

    @FXML
    private Pane idCardToBeat;

    @FXML
    private HBox idDeck;

    @FXML
    void changer(ActionEvent event) {
    	// fonction vidant le deck et le remplissant de nouvelles cartes du deck
    	// et les affichant dans le HBox
    	idDeck.getChildren().clear();
    	
    	
		//ajouter des écouteurs d'événements sur les cartes
		for (int i = 0; i < idDeck.getChildren().size(); i++) {
			ImageView cardImage = (ImageView) idDeck.getChildren().get(i);
			int cardValue = CardGameTools.generateCardValue();
			cardImage.setOnMouseClicked(event1 -> {
				// Afficher la carte sélectionnée
				idCardToBeat.getChildren().clear();
				ImageView selectedCardImage = new ImageView(CardGameTools.loadCardImage(cardValue));
				selectedCardImage.setFitHeight(100);
				selectedCardImage.setFitWidth(70);
				idCardToBeat.getChildren().add(selectedCardImage);
			});
		}
    }

    @FXML
    void quitter(ActionEvent event) {
		// fonction de sortie
		System.exit(0);
    }
    
    @FXML
    void initialize() {
    }

}
