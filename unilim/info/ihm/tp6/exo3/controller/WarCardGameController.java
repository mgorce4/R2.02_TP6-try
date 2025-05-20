package unilim.info.ihm.tp6.exo3.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.TransferMode;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import unilim.info.ihm.tp6.exo3.tools.CardGameTools;
import unilim.info.ihm.tp6.exo3.view.CardImageView;

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
        // Vider le deck
        idDeck.getChildren().clear();

        // Remplir le deck avec 5 nouvelles cartes
        for (int i = 0; i < 5; i++) {
            int cardValue = CardGameTools.generateCardValue();
            ImageView cardImageView = new ImageView(CardGameTools.loadCardImage(cardValue));
            cardImageView.setFitHeight(100);
            cardImageView.setFitWidth(70);

            // Ajouter un écouteur au clic pour afficher la carte sélectionnée
            cardImageView.setOnMouseClicked(e -> {
                idCardToBeat.getChildren().clear();
                ImageView selectedCardImage = new ImageView(CardGameTools.loadCardImage(cardValue));
                selectedCardImage.setFitHeight(100);
                selectedCardImage.setFitWidth(70);
                idCardToBeat.getChildren().add(selectedCardImage);
            });

            // Ajouter la carte dans le deck (HBox)
            idDeck.getChildren().add(cardImageView);
        }
    }


    @FXML
    void quitter(ActionEvent event) {
		// fonction de sortie
		System.exit(0);
    }
    
    @FXML
    void initialize() {
        // Carte cible (idCardToBeat)
        int cardValue = CardGameTools.generateCardValue();
        ImageView cardToBeat = new ImageView(CardGameTools.loadCardImage(cardValue));
        cardToBeat.setFitHeight(100);
        cardToBeat.setFitWidth(70);
        idCardToBeat.getChildren().add(cardToBeat);

        // Target : accepter le drop
        cardToBeat.setOnDragOver(event -> {
            if (event.getGestureSource() != cardToBeat && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        cardToBeat.setOnDragDropped(event -> {
            javafx.scene.input.Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                int draggedValue = Integer.parseInt(db.getString());

                idCardToBeat.getChildren().clear();

                ImageView newCard = new ImageView(CardGameTools.loadCardImage(draggedValue));
                newCard.setFitHeight(100);
                newCard.setFitWidth(70);
                idCardToBeat.getChildren().add(newCard);

                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

        // Initialiser les cartes du deck (sources du drag)
        for (int i = 0; i < 5; i++) {
            final int value = CardGameTools.generateCardValue(); // final pour utiliser dans listener
            ImageView cardImageView = new ImageView(CardGameTools.loadCardImage(value));
            cardImageView.setFitHeight(100);
            cardImageView.setFitWidth(70);

            cardImageView.setOnDragDetected(event -> {
                javafx.scene.input.Dragboard db = cardImageView.startDragAndDrop(TransferMode.MOVE);
                javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
                content.putString(String.valueOf(value));
                db.setContent(content);
                event.consume();
            });

            cardImageView.setOnDragDone(event -> {
                if (event.getTransferMode() == TransferMode.MOVE) {
                    idDeck.getChildren().remove(cardImageView);
                }
                event.consume();
            });

            idDeck.getChildren().add(cardImageView);
        }
    }

    

    public static void manageSourceDnd(CardImageView source, HBox deck) {
        source.setOnDragDetected(event -> {
            javafx.scene.input.Dragboard db = source.startDragAndDrop(TransferMode.MOVE);

            // On met dans le Dragboard un simple contenu texte (par ex l'index ou valeur)  
            // Ici on utilise l'URL de l'image comme identifiant
            db.setDragView(source.getImage());
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(source.getImage().impl_getUrl()); // ou autre identifiant unique
            db.setContent(content);

            source.setOpacity(0.5);
            event.consume();
        });

        source.setOnDragDone(event -> {
            source.setOpacity(1);
            if (event.getTransferMode() == TransferMode.MOVE) {
                // Suppression de la carte source du deck
                deck.getChildren().remove(source);
            }
            event.consume();
        });
    }


    public static void manageTargetDnd(CardImageView target, HBox deck) {
        target.setOnDragOver(event -> {
            if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        target.setOnDragDropped(event -> {
            javafx.scene.input.Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                // Remplacer l'image de la carte cible par celle envoyée
                String imageUrl = db.getString();
                target.setImage(new javafx.scene.image.Image(imageUrl));
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }



}
